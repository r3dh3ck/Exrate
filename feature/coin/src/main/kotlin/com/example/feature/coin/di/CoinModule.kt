package com.example.feature.coin.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.data.CoinRepositoryImpl
import com.example.feature.coin.domain.CoinRepository
import com.example.network.CoingeckoApi
import dagger.Module
import dagger.Provides

@Module
internal object CoinModule {

    @CoinScope
    @Provides
    fun provideCoinRepository(
        api: CoingeckoApi,
        dataStore: DataStore<Preferences>
    ): CoinRepository {
        val currencyDataStore = CurrencyDataStore(dataStore)
        return CoinRepositoryImpl(api, currencyDataStore)
    }
}