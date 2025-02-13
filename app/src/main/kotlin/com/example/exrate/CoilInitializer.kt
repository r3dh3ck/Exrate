package com.example.exrate

import android.app.Application
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import okhttp3.OkHttpClient

class CoilInitializer(
    private val context: Application,
    private val okHttpClient: OkHttpClient
) {

    fun initialize() {
        SingletonImageLoader.setSafe {
            ImageLoader.Builder(context)
                .components {
                    val networkComponent = OkHttpNetworkFetcherFactory(
                        callFactory = { okHttpClient }
                    )
                    add(networkComponent)
                }
                .build()
        }
    }
}