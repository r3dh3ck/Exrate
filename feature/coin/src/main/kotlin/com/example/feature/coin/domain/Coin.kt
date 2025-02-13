package com.example.feature.coin.domain

import com.example.feature.currency.Currency
import java.math.BigDecimal

internal data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val price: BigDecimal,
    val marketCap: Long,
    val rank: String,
    val currency: Currency
)