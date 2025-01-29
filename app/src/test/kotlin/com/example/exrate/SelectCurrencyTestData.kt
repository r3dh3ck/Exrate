package com.example.exrate

import com.example.feature.currency.Currency
import com.example.feature.currency.SelectableCurrency
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

val eurCurrency: Currency = Currency("eur")

val currencyResponseList: List<String> = listOf("usd", "eur", "btc")

val currencyListUsdSelected: ImmutableList<SelectableCurrency> = persistentListOf(
    SelectableCurrency(
        currency = Currency.USD,
        isSelected = true
    ),
    SelectableCurrency(
        currency = Currency("eur"),
        isSelected = false
    ),
    SelectableCurrency(
        currency = Currency("btc"),
        isSelected = false
    )
)

val currencyListEurQueried: ImmutableList<SelectableCurrency> = persistentListOf(
    SelectableCurrency(
        currency = Currency("eur"),
        isSelected = false
    )
)