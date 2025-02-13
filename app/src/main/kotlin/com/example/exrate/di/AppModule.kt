package com.example.exrate.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.currency.CurrencyRepository
import com.example.feature.currency.CurrencyRepositoryImpl
import com.example.network.CoingeckoApi
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.io.File

@Module
object AppModule {

    @Provides
    @AppScope
    fun provideOkhttpClient(context: Application): OkHttpClient {
        val size10mb = 10L * 1024L * 1024L
        val cache = Cache(
            directory = File(context.cacheDir, "okHttp_cache"),
            maxSize = size10mb
        )
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
        val contentType = "application/json; charset=UTF8".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl("http://127.0.0.1")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideCoingeckoApi(retrofit: Retrofit): CoingeckoApi {
        val retrofit = retrofit.newBuilder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .build()
        return retrofit.create<CoingeckoApi>()
    }

    @Provides
    @AppScope
    fun providePreferencesDataStore(context: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("preferences") }
        )
    }

    @Provides
    @AppScope
    fun provideCurrencyRepository(
        api: CoingeckoApi,
        dataStore: DataStore<Preferences>
    ): CurrencyRepository {
        val currencyDataStore = CurrencyDataStore(dataStore)
        return CurrencyRepositoryImpl(api, currencyDataStore)
    }
}