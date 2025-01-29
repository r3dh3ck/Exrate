package com.example.feature.coin

import com.example.feature.coin.domain.Coin
import com.example.feature.currency.Currency
import com.example.network.CoinResponse
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

val coinResponseList: List<CoinResponse> = listOf(
    CoinResponse(
        id = "btc",
        name = "Bitcoin",
        currentPrice = "95000",
        marketCap = "950000",
        marketCapRank = "1"
    ),
    CoinResponse(
        id = "eth",
        name = "Ethereum",
        currentPrice = "3400",
        marketCap = "34000",
        marketCapRank = "2"
    )
)

val coinList: ImmutableList<Coin> = persistentListOf(
    Coin(
        id = "btc",
        name = "Bitcoin",
        price = "95000",
        marketCap = "950000",
        rank = "1",
        currency = Currency.USD
    ),
    Coin(
        id = "eth",
        name = "Ethereum",
        price = "3400",
        marketCap = "34000",
        rank = "2",
        currency = Currency.USD
    )
)

val ethereumCoin: Coin = coinList[1]