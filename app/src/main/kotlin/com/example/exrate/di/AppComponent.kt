package com.example.exrate.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.feature.currency.CurrencyRepository
import com.example.exrate.Initializer
import com.example.network.CoingeckoApi
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    val initializer: Initializer

    val currencyRepository: CurrencyRepository

    val coingeckoApi: CoingeckoApi

    val dataStore: DataStore<Preferences>

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            context: Application
        ): AppComponent
    }
}