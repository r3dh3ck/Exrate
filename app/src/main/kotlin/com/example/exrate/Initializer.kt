package com.example.exrate

import com.example.feature.currency.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Initializer(
    private val currencyRepository: CurrencyRepository
) {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun initialize() {
        scope.launch {
            currencyRepository.getCurrencies()
        }
    }
}