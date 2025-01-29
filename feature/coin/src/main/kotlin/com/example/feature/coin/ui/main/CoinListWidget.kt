package com.example.feature.coin.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.core.lazyListItemPosition
import com.example.feature.coin.domain.Coin
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val COIN_LIST_WIDGET_TEST_TAG = "coin_list_widget_test_tag"
const val COIN_ITEM_PRICE_TEST_TAG = "coin_item_price_test_tag"

@Composable
internal fun CoinListWidget(
    state: CoinListState,
    onCoinClicked: (Coin) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    when (state) {
        is CoinListState.Loading -> Loading(modifier)
        is CoinListState.Content -> Content(
            items = state.items,
            lazyListState = lazyListState,
            onCoinClicked = onCoinClicked,
            modifier = modifier
        )
        is CoinListState.Error -> Error(modifier)
    }
}

@Composable
private fun Loading(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Content(
    items: ImmutableList<Coin>,
    lazyListState: LazyListState,
    onCoinClicked: (Coin) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier.testTag(COIN_LIST_WIDGET_TEST_TAG)
    ) {
        itemsIndexed(items) { index, coin ->
            CoinItem(
                coin = coin,
                modifier = Modifier.padding(top = 2.dp)
                    .clickable { onCoinClicked(coin) }
                    .lazyListItemPosition(index)
            )
        }
    }
}

@Composable
private fun Error(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.internal_error),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun CoinItem(coin: Coin, modifier: Modifier) {
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
                .testTag(COIN_ITEM_PRICE_TEST_TAG)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    CoinListWidget(
        state = CoinListState.Loading,
        onCoinClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    val items = persistentListOf(
        Coin.newInstance(name = "Bitcoin", price = "100000"),
        Coin.newInstance(name = "Ethereum", price = "3500.53"),
        Coin.newInstance(name = "USDT", price = "0.99")
    )
    val state = CoinListState.Content(items)
    CoinListWidget(
        state = state,
        onCoinClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    CoinListWidget(
        state = CoinListState.Error,
        onCoinClicked = {}
    )
}