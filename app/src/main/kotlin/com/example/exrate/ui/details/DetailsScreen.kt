package com.example.exrate.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.exrate.domain.Currency

const val DETAILS_SCREEN_TEST_TAG = "details_screen_test_tag"

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    currencyId: String,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Screen(state, onBack)
    LaunchedEffect(Unit) {
        viewModel.getCurrency(currencyId)
    }
}

@Composable
private fun Screen(
    state: DetailsState,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(DETAILS_SCREEN_TEST_TAG),
        topBar = {
            TopAppBarWidget(
                state = state.topAppBar,
                onBack = onBack
            )
        }
    ) { paddingValues ->
        CurrencyDetailsWidget(
            state = state.currencyDetails,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    val currency = Currency.newInstance(
        name = "Ethereum",
        price = "3250.50",
        marketCap = "1000000000",
        rank = "2"
    )
    val state = DetailsState(
        topAppBar = TopAppBarState.Title(currency.name),
        currencyDetails = CurrencyDetailsState.Content(currency)
    )
    Screen(state) {}
}