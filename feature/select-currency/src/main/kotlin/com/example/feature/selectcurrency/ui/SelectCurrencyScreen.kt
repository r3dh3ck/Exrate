package com.example.feature.selectcurrency.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature.currency.Currency
import com.example.feature.currency.SelectableCurrency
import com.example.feature.selectcurrency.R
import com.example.feature.selectcurrency.di.SelectCurrencyComponentHolder
import com.example.widget.TopAppBarWidget
import kotlinx.collections.immutable.persistentListOf

const val SELECT_CURRENCY_SCREEN_TEST_TAG = "select_currency_screen_test_tag"

@Composable
internal fun SelectCurrencyScreen(
    onBack: () -> Unit,
    onCurrencySelected: () -> Unit
) {
    val component = SelectCurrencyComponentHolder.get()
    val viewModelFactory = component.viewModelFactory
    val viewModel = viewModel<SelectCurrencyViewModel>(factory = viewModelFactory)
    SelectCurrencyEffect(viewModel, onCurrencySelected)
    SelectCurrencyScreen(viewModel, onBack)
}

@Composable
private fun SelectCurrencyEffect(
    viewModel: SelectCurrencyViewModel,
    onBack: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is SelectCurrencyEffect.Back -> onBack()
                }
            }
        }
    }
}

@Composable
private fun SelectCurrencyScreen(
    viewModel: SelectCurrencyViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED
    )
    SelectCurrencyScreen(
        state = state,
        onBack = onBack,
        onQueryChanged = remember { viewModel::query },
        onCurrencyClicked = remember { viewModel::selectCurrency }
    )
    LaunchedEffect(Unit) {
        viewModel.load()
    }
}

@Composable
private fun SelectCurrencyScreen(
    state: SelectCurrencyState,
    onBack: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onCurrencyClicked: (Currency) -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(SELECT_CURRENCY_SCREEN_TEST_TAG),
        topBar = {
            TopAppBarWidget(
                title = stringResource(R.string.select_currency_title),
                onBack = onBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        ) {
            QueryWidget(
                query = state.query,
                onQueryChanged = onQueryChanged
            )
            CurrencyListWidget(
                state = state.currencyList,
                onCurrencyClicked = onCurrencyClicked,
                contentPadding = WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Bottom)
                    .asPaddingValues()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SelectCurrencyScreenPreview() {
    val currencyList = persistentListOf(
        SelectableCurrency(
            currency = Currency("usd"),
            isSelected = true
        ),
        SelectableCurrency(
            currency = Currency("btc"),
            isSelected = true
        ),
        SelectableCurrency(
            currency = Currency("eur"),
            isSelected = false
        )
    )
    val state = SelectCurrencyState(
        query = "usd",
        currencyList = CurrencyListState.Content(currencyList)
    )
    SelectCurrencyScreen(
        state = state,
        onBack = {},
        onQueryChanged = {},
        onCurrencyClicked = {}
    )
}