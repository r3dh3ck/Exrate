package com.example.feature.coin.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature.coin.di.CoinComponentHolder
import com.example.feature.coin.domain.Coin
import kotlinx.collections.immutable.persistentListOf

const val MAIN_SCREEN_TEST_TAG = "main_screen_test_tag"

@Composable
internal fun MainScreen(
    onCoinClicked: (Coin) -> Unit,
    onSettingsClicked: () -> Unit
) {
    val component = CoinComponentHolder.get()
    val viewModelFactory = component.viewModelFactory
    val viewModel = viewModel<MainViewModel>(factory = viewModelFactory)
    MainScreen(
        viewModel = viewModel,
        onCoinClicked = onCoinClicked,
        onSettingsClicked = onSettingsClicked
    )
}

@Composable
private fun MainScreen(
    viewModel: MainViewModel,
    onCoinClicked: (Coin) -> Unit,
    onSettingsClicked: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED
    )
    MainScreen(
        state = state,
        onCoinClicked = onCoinClicked,
        onSettingsClicked = onSettingsClicked
    )
    LaunchedEffect(Unit) {
        viewModel.getCoinList()
    }
}

@Composable
private fun MainScreen(
    state: CoinListState,
    onCoinClicked: (Coin) -> Unit,
    onSettingsClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(MAIN_SCREEN_TEST_TAG),
        topBar = { MainTopAppBarWidget(onSettingsClicked) }
    ) { paddingValues ->
        CoinListWidget(
            state = state,
            onCoinClicked = onCoinClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    val items = persistentListOf(
        Coin.newInstance(name = "Bitcoin", price = "100000"),
        Coin.newInstance(name = "Ethereum", price = "3500.53"),
        Coin.newInstance(name = "USDT", price = "0.99")
    )
    val state = CoinListState.Content(items)
    MainScreen(
        state = state,
        onCoinClicked = {},
        onSettingsClicked = {}
    )
}