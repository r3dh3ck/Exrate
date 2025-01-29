package com.example.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoinResponse(
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