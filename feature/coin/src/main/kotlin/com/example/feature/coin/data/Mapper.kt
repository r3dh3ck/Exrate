package com.example.feature.coin.data

import com.example.feature.coin.domain.Coin
import com.example.feature.currency.Currency
import com.example.network.CoinResponse
import java.math.BigDecimal

internal fun CoinResponse.toCoin(currency: Currency): Coin {
    return Coin(
        id = id,
        symbol = symbol,
        name = name,
        imageUrl = image,
        price = BigDecimal(currentPrice),
        marketCap = marketCap,
        rank = marketCapRank,
        currency = currency
    )
}