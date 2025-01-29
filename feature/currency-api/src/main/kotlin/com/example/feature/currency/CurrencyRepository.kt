package com.example.feature.currency

interface CurrencyRepository {
    suspend fun getCurrencies(): Result<List<Currency>>
    suspend fun queryCurrencies(query: String): Result<List<SelectableCurrency>>
    suspend fun getSelectedCurrency(): Currency
    suspend fun setSelectedCurrency(currency: Currency)
}