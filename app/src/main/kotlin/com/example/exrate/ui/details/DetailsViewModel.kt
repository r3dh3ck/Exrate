package com.example.exrate.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exrate.domain.Currency
import com.example.exrate.domain.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(createEmptyState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun getCurrency(id: String) {
        viewModelScope.launch {
            val result = repository.getCurrency(id)
            val newState = result.fold(
                onSuccess = ::createSuccessState,
                onFailure = { createErrorState() }
            )
            _state.update { newState }
        }
    }

    private fun createEmptyState(): DetailsState {
        return DetailsState(
            topAppBar = TopAppBarState.Empty,
            currencyDetails = CurrencyDetailsState.Empty
        )
    }

    private fun createSuccessState(currency: Currency): DetailsState {
        return DetailsState(
            topAppBar = TopAppBarState.Title(currency.name),
            currencyDetails = CurrencyDetailsState.Content(currency)
        )
    }

    private fun createErrorState(): DetailsState {
        return DetailsState(
            topAppBar = TopAppBarState.Empty,
            currencyDetails = CurrencyDetailsState.Error
        )
    }
}