package com.example.feature.coin.ui.main.coinlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.coin.domain.Coin

const val COIN_LIST_ITEM_PRICE_TEST_TAG = "coin_list_item_price_test_tag"

data object CoinListItem

@Composable
internal fun CoinListItem(coin: Coin, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        Text(
            text = coin.name,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "${coin.price} ${coin.currency.name.uppercase()}",
            modifier = Modifier.align(Alignment.CenterEnd)
                .testTag(COIN_LIST_ITEM_PRICE_TEST_TAG)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinListItemPreview() {
    val coin = Coin.newInstance(name = "Bitcoin", price = "100000")
    CoinListItem(coin)
}