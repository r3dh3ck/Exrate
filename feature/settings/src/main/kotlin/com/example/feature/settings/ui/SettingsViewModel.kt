package com.example.feature.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.currency.Currency
import com.example.feature.currency.CurrencyRepository
import com.example.feature.settings.di.SettingsComponentHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(createInitState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    override fun onCleared() {
        SettingsComponentHolder.clear()
    }

    fun load() {
        viewModelScope.launch {
            val currency = repository.getSelectedCurrency()
            _state.update { it.copy(selectedCurrency = currency) }
        }
    }

    private fun createInitState(): SettingsState {
        return SettingsState(
            selectedCurrency = Currency.USD
        )
    }
}