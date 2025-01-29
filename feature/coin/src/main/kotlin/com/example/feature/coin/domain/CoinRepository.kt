package com.example.feature.coin.domain

interface CoinRepository {
    suspend fun getCoinList(): Result<List<Coin>>
    suspend fun getCoin(id: String): Result<Coin>
}