package com.example.network

import retrofit2.http.GET
import retrofit2.http.Query

interface CoingeckoApi {

    @GET("coins/markets")
    suspend fun getMarkets(
        @Query("vs_currency")
        vsCurrency: String,
        @Query("per_page")
        perPage: Int,
        @Query("page")
        page: Int
    ): List<CoinResponse>

    @GET("simple/supported_vs_currencies")
    suspend fun getSupportedCurrencies(): List<String>
}