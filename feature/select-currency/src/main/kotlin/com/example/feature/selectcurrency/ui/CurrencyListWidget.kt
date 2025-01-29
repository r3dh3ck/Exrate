package com.example.feature.selectcurrency.ui

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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.core.lazyListItemPosition
import com.example.feature.currency.Currency
import com.example.feature.currency.SelectableCurrency
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

const val CURRENCY_LIST_WIDGET_TEST_TAG = "currency_list_widget_test_tag"
const val CURRENCY_ITEM_NAME_TEST_TAG = "currency_item_name_test_tag"
const val CURRENCY_ITEM_CHECK_ICON_TEST_TAG = "currency_item_check_icon_test_tag"

@Composable
internal fun CurrencyListWidget(
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
    items: ImmutableList<SelectableCurrency>,
    onCurrencyClicked: (Currency) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.testTag(CURRENCY_LIST_WIDGET_TEST_TAG)
    ) {
        itemsIndexed(items) { index, selectableCurrency ->
            CurrencyItem(
                selectableCurrency = selectableCurrency,
                modifier = Modifier.padding(top = 2.dp)
                    .clickable { onCurrencyClicked(selectableCurrency.currency) }
                    .lazyListItemPosition(index)
            )
        }
    }
}

@Composable
private fun CurrencyItem(
    selectableCurrency: SelectableCurrency,
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        Text(
            text = selectableCurrency.currency.name.uppercase(),
            modifier = Modifier.align(Alignment.CenterStart)
                .testTag(CURRENCY_ITEM_NAME_TEST_TAG)
        )
        if (selectableCurrency.isSelected) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
                    .testTag(CURRENCY_ITEM_CHECK_ICON_TEST_TAG)
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