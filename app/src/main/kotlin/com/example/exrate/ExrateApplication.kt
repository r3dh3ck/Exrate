package com.example.exrate

import android.app.Application
import com.example.exrate.di.AppComponent
import com.example.exrate.di.DaggerAppComponent

class ExrateApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}