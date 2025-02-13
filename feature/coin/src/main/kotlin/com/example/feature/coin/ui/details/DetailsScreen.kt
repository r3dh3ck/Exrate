package com.example.feature.coin.ui.details

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailsScreenPreview() {
    val coin = CoinDetailsUiModel(
        name = "Ethereum",
        price = "3,250.50 USD",
        marketCap = "1,000,000,000 USD",
        rank = "2"
    )
    val state = DetailsState(
        topAppBarTitle = coin.name,
        coinDetails = CoinDetailsState.Content(coin)
    )
    DetailsScreen(state) {}
}