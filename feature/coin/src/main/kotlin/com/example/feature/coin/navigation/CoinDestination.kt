package com.example.feature.coin.navigation

import kotlinx.serialization.Serializable

@Serializable
data object CoinDestination {

    @Serializable
    internal data object MainScreen

    @Serializable
    internal data class DetailsScreen(
        val coinId: String
    )
}