package com.example.feature.coin.ui.main.coinlist.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

const val COIN_LIST_ITEM_PRICE_TEST_TAG = "coin_list_item_price_test_tag"

internal data object CoinListItem

@Composable
internal fun CoinListItem(coinItem: CoinItem, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        AsyncImage(
            model = coinItem.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(36.dp)
                .padding(start = 4.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 12.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(
                text = coinItem.symbol
            )
            Text(
                text = coinItem.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.caption
            )
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = coinItem.price,
            modifier = Modifier.testTag(COIN_LIST_ITEM_PRICE_TEST_TAG)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinListItemPreview() {
    val coin = CoinItem.newInstance(
        symbol = "btc",
        name = "Bitcoin",
        price = "100,000 USD"
    )
    CoinListItem(coin)
}