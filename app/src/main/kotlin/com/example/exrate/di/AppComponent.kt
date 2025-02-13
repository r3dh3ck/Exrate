package com.example.exrate.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.feature.currency.CurrencyRepository
import com.example.network.CoingeckoApi
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient

@AppScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    val okHttpClient: OkHttpClient

    val coingeckoApi: CoingeckoApi

    val dataStore: DataStore<Preferences>

    val currencyRepository: CurrencyRepository

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            context: Application
        ): AppComponent
    }
}