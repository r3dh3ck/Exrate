package com.example.feature.coin.domain

import com.example.feature.coin.data.CoinRepository
import javax.inject.Inject

internal class CoinPaginationUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    private val resultList = mutableListOf<Coin>()
    private var page = INITIAL_PAGE
    private var endOfPagination = false

    suspend fun refresh(): Result<List<Coin>> {
        resultList.clear()
        page = INITIAL_PAGE
        endOfPagination = false
        return loadMore()
    }

    suspend fun loadMore(): Result<List<Coin>> {
        if (endOfPagination) {
            return Result.success(resultList)
        }
        return repository.getCoinList(
            pageSize = PAGE_SIZE,
            page = page++
        ).onSuccess { coinList ->
            if (coinList.size < PAGE_SIZE) {
                endOfPagination = true
            }
        }.onFailure {
            page--
        }.map { coinList ->
            resultList += coinList
            resultList
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val PAGE_SIZE = 50
    }
}