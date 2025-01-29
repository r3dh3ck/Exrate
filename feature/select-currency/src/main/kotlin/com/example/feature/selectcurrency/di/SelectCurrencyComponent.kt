package com.example.feature.selectcurrency.di

import dagger.Component

@Component(
    dependencies = [SelectCurrencyDependencies::class]
)
interface SelectCurrencyComponent {

    val viewModelFactory: SelectCurrencyViewModelFactory
}