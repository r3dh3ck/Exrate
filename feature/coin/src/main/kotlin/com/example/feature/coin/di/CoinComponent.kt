package com.example.feature.coin.di

import dagger.Component

@CoinScope
@Component(
    modules = [CoinModule::class],
    dependencies = [CoinDependencies::class]
)
interface CoinComponent {

    val viewModelFactory: CoinViewModelFactory
}