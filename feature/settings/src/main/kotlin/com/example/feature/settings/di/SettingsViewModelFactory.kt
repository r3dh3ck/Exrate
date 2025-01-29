package com.example.feature.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.currency.CurrencyRepository
import com.example.feature.settings.ui.SettingsViewModel
import javax.inject.Inject

class SettingsViewModelFactory @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(repository) as T
    }
}