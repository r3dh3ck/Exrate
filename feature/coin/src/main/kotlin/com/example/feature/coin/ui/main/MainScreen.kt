package com.example.feature.coin.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature.coin.di.CoinComponentHolder
import com.example.feature.coin.navigation.SelectCurrencyResult
import com.example.feature.coin.ui.main.coinlist.item.CoinItem
import com.example.feature.coin.ui.main.coinlist.CoinListAction
import com.example.feature.coin.ui.main.coinlist.CoinListState
import com.example.feature.coin.ui.main.coinlist.CoinListWidget
import kotlinx.collections.immutable.persistentListOf
import com.example.core.R as coreR

const val MAIN_SCREEN_TEST_TAG = "main_screen_test_tag"

@Composable
internal fun MainScreen(
    selectCurrencyResult: SelectCurrencyResult,
    onSettingsClicked: () -> Unit,
    onCoinClicked: (String) -> Unit
) {
    val component = CoinComponentHolder.get()
    val viewModelFactory = component.viewModelFactory
    val viewModel = viewModel<MainViewModel>(factory = viewModelFactory)
    val snackbarHostState = remember { SnackbarHostState() }
    MainEffect(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState
    )
    MainScreen(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
        onCoinClicked = onCoinClicked,
        onSettingsClicked = onSettingsClicked
    )
    LaunchedEffect(Unit) {
        viewModel.onSelectCurrencyResult(selectCurrencyResult)
    }
}

@Composable
private fun MainEffect(
    viewModel: MainViewModel,
    snackbarHostState: SnackbarHostState
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is MainEffect.ShowSnackbar -> {
                        snackbarHostState.showSnackbar(
                            message = context.getString(coreR.string.internal_error)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    viewModel: MainViewModel,
    snackbarHostState: SnackbarHostState,
    onSettingsClicked: () -> Unit,
    onCoinClicked: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED
    )
    MainScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onSettingsClicked = onSettingsClicked,
        onCoinListAction = remember {{ action ->
            when (action) {
                is CoinListAction.Retry -> viewModel.retry()
                is CoinListAction.LoadMore -> viewModel.loadMore()
                is CoinListAction.RetryLoadMore -> viewModel.loadMore()
                is CoinListAction.Refresh -> viewModel.refresh()
                is CoinListAction.CoinClicked -> onCoinClicked(action.coinId)
            }
        }}
    )
}

@Composable
private fun MainScreen(
    state: CoinListState,
    snackbarHostState: SnackbarHostState,
    onSettingsClicked: () -> Unit,
    onCoinListAction: (CoinListAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(MAIN_SCREEN_TEST_TAG),
        topBar = { MainTopAppBarWidget(onSettingsClicked) },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.safeContentPadding()
            )
        }
    ) { paddingValues ->
        CoinListWidget(
            state = state,
            onAction = onCoinListAction,
            contentPadding = WindowInsets.safeDrawing
                .only(WindowInsetsSides.Bottom)
                .asPaddingValues(),
            modifier = Modifier.padding(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    val items = persistentListOf(
        CoinItem.newInstance(
            id = "btc",
            symbol = "btc",
            name = "Bitcoin",
            price = "100,000 USD"
        ),
        CoinItem.newInstance(
            id = "eth",
            symbol = "eth",
            name = "Ethereum",
            price = "3,500.53 USD"
        ),
        CoinItem.newInstance(
            id = "usdt",
            symbol = "usdt",
            name = "Tether",
            price = "0.99 USD"
        )
    )
    val state = CoinListState.Content(
        items = items,
        loadMore = CoinListState.LoadState.NotLoaded,
        refresh = CoinListState.LoadState.NotLoaded
    )
    MainScreen(
        state = state,
        snackbarHostState = SnackbarHostState(),
        onSettingsClicked = {},
        onCoinListAction = {}
    )
}