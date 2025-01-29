package com.example.feature.coin.ui.details

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
import com.example.widget.TopAppBarWidget

const val DETAILS_SCREEN_TEST_TAG = "details_screen_test_tag"

@Composable
internal fun DetailsScreen(
    coinId: String,
    onBack: () -> Unit
) {
    val component = CoinComponentHolder.get()
    val viewModelFactory = component.viewModelFactory
    val viewModel = viewModel<DetailsViewModel>(factory = viewModelFactory)
    DetailsScreen(
        coinId = coinId,
        viewModel = viewModel,
        onBack = onBack
    )
}

@Composable
private fun DetailsScreen(
    coinId: String,
    viewModel: DetailsViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED
    )
    DetailsScreen(state, onBack)
    LaunchedEffect(Unit) {
        viewModel.getCoin(coinId)
    }
}

@Composable
private fun DetailsScreen(
    state: DetailsState,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(DETAILS_SCREEN_TEST_TAG),
        topBar = {
            TopAppBarWidget(
                title = state.topAppBarTitle,
                onBack = onBack
            )
        }
    ) { paddingValues ->
        CoinDetailsWidget(
            state = state.coinDetails,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailsScreenPreview() {
    val coin = Coin.newInstance(
        name = "Ethereum",
        price = "3250.50",
        marketCap = "1000000000",
        rank = "2"
    )
    val state = DetailsState(
        topAppBarTitle = coin.name,
        coinDetails = CoinDetailsState.Content(coin)
    )
    DetailsScreen(state) {}
}