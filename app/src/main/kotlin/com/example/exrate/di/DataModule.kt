package com.example.exrate.di

import com.example.exrate.data.CoingeckoApi
import com.example.exrate.data.CurrencyRepositoryImpl
import com.example.exrate.domain.CurrencyRepository
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

@Module
object DataModule {

    @Provides
    @AppScope
    fun provideCurrencyRepository(): CurrencyRepository {
        val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
        val contentType = "application/json; charset=UTF8".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(converterFactory)
            .build()
        val api = retrofit.create<CoingeckoApi>()
        return CurrencyRepositoryImpl(api)
    }
}