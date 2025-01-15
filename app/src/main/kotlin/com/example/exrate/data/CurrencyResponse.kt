package com.example.exrate.data

import com.example.exrate.domain.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CurrencyResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("current_price")
    val currentPrice: String,
    @SerialName("market_cap")
    val marketCap: String,
    @SerialName("market_cap_rank")
    val marketCapRank: String
)

fun CurrencyResponse.toCurrency(): Currency {
    return Currency(
        id = id,
        name = name,
        price = currentPrice,
        marketCap = marketCap,
        rank = marketCapRank
    )
}