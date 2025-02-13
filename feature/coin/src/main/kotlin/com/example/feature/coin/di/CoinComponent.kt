package com.example.feature.coin.di

import dagger.Component

@CoinScope
@Component(
    modules = [CoinModule::class],
    dependencies = [CoinDependencies::class]
)
abstract class CoinComponent {

    internal abstract val viewModelFactory: CoinViewModelFactory
}