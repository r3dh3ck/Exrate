package com.example.exrate

import android.app.Application
import com.example.exrate.di.AppComponent
import com.example.exrate.di.DaggerAppComponent

class ExrateApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        ComponentInitializer(appComponent).initialize()
        CoilInitializer(this, appComponent.okHttpClient).initialize()
        Initializer(appComponent.currencyRepository).initialize()
    }
}