package com.example.exrate.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.exrate.R
import com.example.exrate.domain.Currency
import com.example.exrate.lazyListItemPosition
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val CURRENCY_LIST_WIDGET_TEST_TAG = "currency_list_widget_test_tag"

@Composable
fun CurrencyListWidget(
    state: CurrencyListState,
    onCurrencyClicked: (Currency) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is CurrencyListState.Loading -> Loading(modifier)
        is CurrencyListState.Content -> Content(
            items = state.items,
            onCurrencyClicked = onCurrencyClicked,
            modifier = modifier
        )
        is CurrencyListState.Error -> Error(modifier)
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
    items: ImmutableList<Currency>,
    onCurrencyClicked: (Currency) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.testTag(CURRENCY_LIST_WIDGET_TEST_TAG)
    ) {
        itemsIndexed(items) { index, currency ->
            CurrencyItem(
                currency = currency,
                modifier = Modifier.clickable { onCurrencyClicked(currency) }
                    .lazyListItemPosition(index)
            )
        }
    }
}

@Composable
private fun CurrencyItem(
    currency: Currency,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .padding(top = 2.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        Text(
            text = currency.name,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "$${currency.price}",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
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

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    CurrencyListWidget(
        state = CurrencyListState.Loading,
        onCurrencyClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    val items = persistentListOf(
        Currency.newInstance(name = "Bitcoin", price = "100000"),
        Currency.newInstance(name = "Ethereum", price = "3500.53"),
        Currency.newInstance(name = "USDT", price = "0.99")
    )
    val state = CurrencyListState.Content(items)
    CurrencyListWidget(
        state = state,
        onCurrencyClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    CurrencyListWidget(
        state = CurrencyListState.Error,
        onCurrencyClicked = {}
    )
}