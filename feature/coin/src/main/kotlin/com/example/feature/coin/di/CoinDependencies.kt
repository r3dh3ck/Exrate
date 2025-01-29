package com.example.feature.coin.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.network.CoingeckoApi

interface CoinDependencies {

    val coingeckoApi: CoingeckoApi

    val dataStore: DataStore<Preferences>
}