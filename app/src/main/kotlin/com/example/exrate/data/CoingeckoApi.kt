package com.example.exrate.data

import retrofit2.http.GET

interface CoingeckoApi {
    @GET("coins/markets?vs_currency=usd")
    suspend fun getMarkets(): List<CurrencyResponse>
}