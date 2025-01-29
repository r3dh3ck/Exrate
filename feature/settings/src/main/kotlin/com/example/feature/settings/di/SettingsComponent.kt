package com.example.feature.settings.di

import dagger.Component

@Component(
    dependencies = [SettingsDependencies::class]
)
interface SettingsComponent {

    val viewModelFactory: SettingsViewModelFactory
}