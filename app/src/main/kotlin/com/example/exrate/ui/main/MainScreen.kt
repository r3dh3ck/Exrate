package com.example.exrate.ui.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exrate.R
import com.example.exrate.domain.Currency
import kotlinx.collections.immutable.persistentListOf

const val MAIN_SCREEN_TEST_TAG = "main_screen_test_tag"

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onCurrencyClicked: (Currency) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Screen(
        state = state,
        onCurrencyClicked = onCurrencyClicked
    )
    LaunchedEffect(Unit) {
        viewModel.getCurrencyList()
    }
}

@Composable
private fun Screen(
    state: CurrencyListState,
    onCurrencyClicked: (Currency) -> Unit,
) {
    Scaffold(
        modifier = Modifier.testTag(MAIN_SCREEN_TEST_TAG),
        topBar = { TopAppBarWidget() }
    ) { paddingValues ->
        CurrencyListWidget(
            state = state,
            onCurrencyClicked = onCurrencyClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun TopAppBarWidget() {
    return TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        windowInsets = WindowInsets.statusBars,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    val items = persistentListOf(
        Currency.newInstance(name = "Bitcoin", price = "100000"),
        Currency.newInstance(name = "Ethereum", price = "3500.53"),
        Currency.newInstance(name = "USDT", price = "0.99")
    )
    val state = CurrencyListState.Content(items)
    Screen(
        state = state,
        onCurrencyClicked = {}
    )
}