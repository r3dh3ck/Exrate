package com.example.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoinResponse(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("current_price")
    val currentPrice: String,
    @SerialName("market_cap")
    val marketCap: Long,
    @SerialName("market_cap_rank")
    val marketCapRank: String
)