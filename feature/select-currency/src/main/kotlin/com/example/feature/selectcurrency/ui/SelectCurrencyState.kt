package com.example.feature.selectcurrency.ui

import androidx.compose.runtime.Immutable

@Immutable
data class SelectCurrencyState(
    val query: String,
    val currencyList: CurrencyListState
)