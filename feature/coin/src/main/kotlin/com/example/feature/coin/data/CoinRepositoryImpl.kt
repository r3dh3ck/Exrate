package com.example.feature.coin.data

import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.domain.Coin
import com.example.feature.coin.domain.CoinRepository
import com.example.feature.currency.Currency
import com.example.network.CoingeckoApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class CoinRepositoryImpl(
    private val coingeckoApi: CoingeckoApi,
    private val dataStore: CurrencyDataStore
) : CoinRepository {

    @Volatile
    private var coinCache: CoinCache? = null
    private val mutex = Mutex()

    override suspend fun getCoinList(): Result<List<Coin>> {
        return runCatching {
            val currency = dataStore.getSelectedCurrency()
            getCoinList(currency)
        }
    }

    override suspend fun getCoin(id: String): Result<Coin> {
        val coin = coinCache?.list?.find { currency -> currency.id == id }
        return if (coin != null) {
            Result.success(coin)
        } else {
            val exception = IllegalArgumentException("There is no coin by given id")
            Result.failure(exception)
        }
    }

    private suspend fun getCoinList(currency: Currency): List<Coin> {
        var cache = coinCache
        if (cache != null && cache.currency == currency) {
            return cache.list
        }
        mutex.withLock {
            cache = coinCache
            if (cache != null && cache.currency == currency) {
                return cache.list
            }
            val coinList = coingeckoApi.getMarkets(currency.name)
                .map { currencyResponse -> currencyResponse.toCoin(currency) }
            coinCache = CoinCache(coinList, currency)
            return coinList
        }
    }

    private class CoinCache(
        val list: List<Coin>,
        val currency: Currency
    )
}