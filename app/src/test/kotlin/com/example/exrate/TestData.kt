package com.example.exrate

import com.example.exrate.data.CurrencyResponse
import com.example.exrate.domain.Currency
import kotlinx.collections.immutable.persistentListOf

val currencyResponseList = listOf(
    CurrencyResponse(
        id = "btc",
        name = "Bitcoin",
        currentPrice = "95000",
        marketCap = "950000",
        marketCapRank = "1"
    ),
    CurrencyResponse(
        id = "eth",
        name = "Ethereum",
        currentPrice = "3400",
        marketCap = "34000",
        marketCapRank = "2"
    )
)

val currencyList = persistentListOf(
    Currency(
        id = "btc",
        name = "Bitcoin",
        price = "95000",
        marketCap = "950000",
        rank = "1"
    ),
    Currency(
        id = "eth",
        name = "Ethereum",
        price = "3400",
        marketCap = "34000",
        rank = "2"
    )
)

val ethereumCurrency = currencyList[1]