package com.example.feature.coin.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.coin.R
import com.example.feature.coin.domain.Coin
import com.example.feature.currency.Currency
import com.example.widget.OneLineWidget
import com.example.core.R as coreR

const val COIN_DETAILS_WIDGET_MARKET_CAP_TEST_TAG = "coin_details_widget_market_cap_test_tag"
const val COIN_DETAILS_WIDGET_PRICE_TEST_TAG = "coin_details_widget_test_tag"

@Composable
internal fun CoinDetailsWidget(
    state: CoinDetailsState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is CoinDetailsState.Content -> Content(state.coin, modifier)
        is CoinDetailsState.Error -> Error(modifier)
        is CoinDetailsState.Empty -> Unit
    }
}

@Composable
private fun Content(coin: Coin, modifier: Modifier) {
    Column(modifier) {
        OneLineWidget(
            label = stringResource(R.string.coin_details_market_cap_rank),
            value = coin.rank,
            modifier = Modifier.padding(top = 2.dp)
        )
        OneLineWidget(
            label = stringResource(R.string.coin_details_market_cap),
            value = "${coin.marketCap} ${coin.currency.name.uppercase()}",
            modifier = Modifier.padding(top = 2.dp)
                .testTag(COIN_DETAILS_WIDGET_MARKET_CAP_TEST_TAG)
        )
        OneLineWidget(
            label = stringResource(R.string.coin_details_current_price),
            value = "${coin.price} ${coin.currency.name.uppercase()}",
            modifier = Modifier.padding(top = 2.dp)
                .testTag(COIN_DETAILS_WIDGET_PRICE_TEST_TAG)
        )
    }
}

@Composable
private fun Error(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(coreR.string.internal_error),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsWidgetContentPreview() {
    val coin = Coin.newInstance(
        price = "3250.50",
        marketCap = "1000000000",
        rank = "2",
        currency = Currency(name = "usd")
    )
    val state = CoinDetailsState.Content(coin)
    CoinDetailsWidget(state)
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsWidgetErrorPreview() {
    CoinDetailsWidget(CoinDetailsState.Error)
}