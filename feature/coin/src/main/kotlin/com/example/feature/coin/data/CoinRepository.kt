package com.example.feature.coin.data

import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.domain.Coin
import com.example.network.CoingeckoApi

internal class CoinRepository(
    private val coingeckoApi: CoingeckoApi,
    private val dataStore: CurrencyDataStore
) {

    private val coinCache = mutableMapOf<String, Coin>()

    suspend fun getCoinList(pageSize: Int, page: Int): Result<List<Coin>> {
        return runCatching {
            val currency = dataStore.getSelectedCurrency()
            val coinList = coingeckoApi.getMarkets(
                vsCurrency = currency.name,
                perPage = pageSize,
                page = page
            ).map { coinResponse ->
                coinResponse.toCoin(currency)
            }
            coinList.associateByTo(coinCache) { coin -> coin.id }
            coinList
        }
    }

    fun getCoin(id: String): Result<Coin> {
        val coin = coinCache[id]
        return if (coin != null) {
            Result.success(coin)
        } else {
            val exception = IllegalArgumentException("There is no coin by given id")
            Result.failure(exception)
        }
    }
}