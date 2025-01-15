package com.example.exrate.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exrate.R
import com.example.exrate.domain.Currency

@Composable
fun CurrencyDetailsWidget(
    state: CurrencyDetailsState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is CurrencyDetailsState.Content -> Content(state.currency, modifier)
        CurrencyDetailsState.Error -> Error(modifier)
        CurrencyDetailsState.Empty -> Unit
    }
}

@Composable
private fun Content(currency: Currency, modifier: Modifier) {
    Column(modifier) {
        InfoItem(
            label = stringResource(R.string.currency_details_market_cap_rank),
            value = currency.rank
        )
        InfoItem(
            label = stringResource(R.string.currency_details_market_cap),
            value = "$${currency.marketCap}"
        )
        InfoItem(
            label = stringResource(R.string.currency_details_current_price),
            value = "$${currency.price}"
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

@Composable
private fun InfoItem(label: String, value: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .padding(top = 2.dp)
            .background(color = MaterialTheme.colors.surface)
            .padding(12.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = value,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrencyDetailsWidgetContentPreview() {
    val currency = Currency.newInstance(
        price = "3250.50",
        marketCap = "1000000000",
        rank = "2"
    )
    val state = CurrencyDetailsState.Content(currency)
    CurrencyDetailsWidget(state)
}

@Preview(showBackground = true)
@Composable
private fun CurrencyDetailsWidgetErrorPreview() {
    CurrencyDetailsWidget(CurrencyDetailsState.Error)
}