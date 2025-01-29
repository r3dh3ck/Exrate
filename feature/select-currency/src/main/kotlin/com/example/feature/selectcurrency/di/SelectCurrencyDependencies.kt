package com.example.feature.selectcurrency.di

import com.example.feature.currency.CurrencyRepository

interface SelectCurrencyDependencies {

    val currencyRepository: CurrencyRepository
}