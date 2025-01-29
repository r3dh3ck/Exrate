package com.example.feature.currency

import com.example.datastore.currency.CurrencyDataStore
import com.example.network.CoingeckoApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CurrencyRepositoryImpl(
    private val api: CoingeckoApi,
    private val dataStore: CurrencyDataStore
) : CurrencyRepository {

    @Volatile
    private var currencyCache: List<Currency>? = null
    private val mutex = Mutex()

    override suspend fun getCurrencies(): Result<List<Currency>> {
        return runCatching {
            getCurrencyList()
        }
    }

    override suspend fun queryCurrencies(query: String): Result<List<SelectableCurrency>> {
        return runCatching {
            coroutineScope {
                val selectedCurrencyDeferred = async { getSelectedCurrency() }
                val currencyList = getCurrencyList()
                val selectedCurrency = selectedCurrencyDeferred.await()
                currencyList.filter { currency ->
                    currency.name.contains(query, ignoreCase = true)
                }.map { currency ->
                    createSelectableCurrency(currency, selectedCurrency)
                }
            }
        }
    }

    override suspend fun getSelectedCurrency(): Currency {
        return dataStore.getSelectedCurrency()
    }

    override suspend fun setSelectedCurrency(currency: Currency) {
        dataStore.setSelectedCurrency(currency)
    }

    private suspend fun getCurrencyList(): List<Currency> {
        var currencyList = currencyCache
        if (currencyList != null) {
            return currencyList
        }
        mutex.withLock {
            currencyList = currencyCache
            if (currencyList != null) {
                return currencyList
            }
            currencyList = api.getSupportedCurrencies().map(::Currency)
            currencyCache = currencyList
            return currencyList
        }
    }

    private fun createSelectableCurrency(
        currency: Currency,
        selectedCurrency: Currency
    ): SelectableCurrency {
        return SelectableCurrency(
            currency = currency,
            isSelected = currency == selectedCurrency
        )
    }
}