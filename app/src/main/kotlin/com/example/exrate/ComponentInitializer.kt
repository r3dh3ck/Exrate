package com.example.exrate

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.exrate.di.AppComponent
import com.example.feature.coin.di.CoinComponentHolder
import com.example.feature.coin.di.CoinDependencies
import com.example.feature.coin.di.DaggerCoinComponent
import com.example.feature.currency.CurrencyRepository
import com.example.feature.selectcurrency.di.DaggerSelectCurrencyComponent
import com.example.feature.selectcurrency.di.SelectCurrencyComponentHolder
import com.example.feature.selectcurrency.di.SelectCurrencyDependencies
import com.example.feature.settings.di.DaggerSettingsComponent
import com.example.feature.settings.di.SettingsComponentHolder
import com.example.feature.settings.di.SettingsDependencies
import com.example.network.CoingeckoApi

class ComponentInitializer(
    private val appComponent: AppComponent
) {

    fun initialize() {
        val coinDeps = object : CoinDependencies {
            override val coingeckoApi: CoingeckoApi get() = appComponent.coingeckoApi
            override val dataStore: DataStore<Preferences> get() = appComponent.dataStore
        }
        CoinComponentHolder.set {
            DaggerCoinComponent.builder()
                .coinDependencies(coinDeps)
                .build()
        }
        val settingsDeps = object : SettingsDependencies {
            override val currencyRepository: CurrencyRepository get() {
                return appComponent.currencyRepository
            }
        }
        SettingsComponentHolder.set {
            DaggerSettingsComponent.builder()
                .settingsDependencies(settingsDeps)
                .build()
        }
        val selectCurrencyDeps = object : SelectCurrencyDependencies {
            override val currencyRepository: CurrencyRepository get() {
                return appComponent.currencyRepository
            }
        }
        SelectCurrencyComponentHolder.set {
            DaggerSelectCurrencyComponent.builder()
                .selectCurrencyDependencies(selectCurrencyDeps)
                .build()
        }
    }
}