package com.example.exrate.di

import dagger.Component

@AppScope
@Component(
    modules = [DataModule::class, PresentationModule::class]
)
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
}