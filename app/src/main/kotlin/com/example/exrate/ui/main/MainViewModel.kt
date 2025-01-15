package com.example.exrate.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exrate.domain.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CurrencyListState>(CurrencyListState.Loading)
    val state: StateFlow<CurrencyListState> = _state.asStateFlow()

    fun getCurrencyList() {
        _state.update { CurrencyListState.Loading }
        viewModelScope.launch {
            val result = currencyRepository.getCurrencyList()
            val newState = result.fold(
                onSuccess = { currencyList -> CurrencyListState.Content(currencyList) },
                onFailure = { CurrencyListState.Error }
            )
            _state.update { newState }
        }
    }
}