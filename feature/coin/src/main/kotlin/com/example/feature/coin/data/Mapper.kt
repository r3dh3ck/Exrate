package com.example.feature.coin.data

import com.example.feature.coin.domain.Coin
import com.example.feature.currency.Currency
import com.example.network.CoinResponse

internal fun CoinResponse.toCoin(currency: Currency): Coin {
    return Coin(
        id = id,
        name = name,
        price = currentPrice,
        marketCap = marketCap,
        rank = marketCapRank,
        currency = currency
    )
}