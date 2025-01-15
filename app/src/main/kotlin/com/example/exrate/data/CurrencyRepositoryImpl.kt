package com.example.exrate.data

import com.example.exrate.domain.Currency
import com.example.exrate.domain.CurrencyRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CurrencyRepositoryImpl(
    private val coingeckoApi: CoingeckoApi
) : CurrencyRepository {

    @Volatile
    private var currencyCache: ImmutableList<Currency>? = null
    private val mutex = Mutex()

    override suspend fun getCurrencyList(): Result<ImmutableList<Currency>> {
        val currencyList = currencyCache
        if (currencyList != null) {
            return Result.success(currencyList)
        }
        mutex.withLock {
            val currencyList = currencyCache
            if (currencyList != null) {
                return Result.success(currencyList)
            }
            return runCatching {
                coingeckoApi.getMarkets()
                    .map { currencyResponse -> currencyResponse.toCurrency() }
                    .toImmutableList()
                    .also { list -> currencyCache = list }
            }
        }
    }

    override suspend fun getCurrency(id: String): Result<Currency> {
        val currency = currencyCache?.find { currency -> currency.id == id }
        return if (currency != null) {
            Result.success(currency)
        } else {
            val exception = IllegalArgumentException("There is no currency by given id")
            Result.failure(exception)
        }
    }
}