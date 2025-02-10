package com.example.feature.coin.domain

interface CoinPaginationUseCase {

    suspend fun refresh(): Result<List<Coin>>

    suspend fun loadMore(): Result<List<Coin>>
}