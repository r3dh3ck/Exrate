package com.example.feature.selectcurrency.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.currency.CurrencyRepository
import com.example.feature.selectcurrency.ui.SelectCurrencyViewModel
import javax.inject.Inject

class SelectCurrencyViewModelFactory @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectCurrencyViewModel(repository) as T
    }
}