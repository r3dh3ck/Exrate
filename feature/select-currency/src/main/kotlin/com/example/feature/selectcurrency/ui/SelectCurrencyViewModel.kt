package com.example.feature.selectcurrency.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.currency.Currency
import com.example.feature.currency.CurrencyRepository
import com.example.feature.currency.SelectableCurrency
import com.example.feature.selectcurrency.di.SelectCurrencyComponentHolder
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectCurrencyViewModel(
    private val repository: CurrencyRepository
) : ViewModel() {

    private var job: Job? = null
    private val _state = MutableStateFlow(createInitState())
    private val _effect = MutableSharedFlow<SelectCurrencyEffect>()
    val state: StateFlow<SelectCurrencyState> = _state.asStateFlow()
    val effect: SharedFlow<SelectCurrencyEffect> = _effect.asSharedFlow()

    override fun onCleared() {
        SelectCurrencyComponentHolder.clear()
    }

    fun load() {
        query(_state.value.query)
    }

    fun query(query: String) {
        _state.update { it.copy(query = query) }
        job?.cancel()
        job = viewModelScope.launch {
            val updatedCurrencyListState = repository.queryCurrencies(query).fold(
                onSuccess = ::createContentCurrencyListState,
                onFailure = { CurrencyListState.Error }
            )
            _state.update { it.copy(currencyList = updatedCurrencyListState) }
        }
    }

    fun selectCurrency(currency: Currency) {
        viewModelScope.launch {
            repository.setSelectedCurrency(currency)
            val effect = SelectCurrencyEffect.Back
            _effect.emit(effect)
        }
    }

    private fun createInitState(): SelectCurrencyState {
        return SelectCurrencyState(
            query = "",
            currencyList = CurrencyListState.Loading
        )
    }

    private fun createContentCurrencyListState(
        currencyList: List<SelectableCurrency>
    ): CurrencyListState.Content {
        return CurrencyListState.Content(
            items = currencyList.toImmutableList()
        )
    }
}