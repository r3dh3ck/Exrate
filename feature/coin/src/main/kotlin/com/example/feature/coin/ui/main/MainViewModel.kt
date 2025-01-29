package com.example.feature.coin.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.coin.domain.Coin
import com.example.feature.coin.domain.CoinRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.Loading)
    val state: StateFlow<CoinListState> = _state.asStateFlow()

    fun getCoinList() {
        _state.update { CoinListState.Loading }
        viewModelScope.launch {
            val result = repository.getCoinList()
            val newState = result.fold(
                onSuccess = ::createSuccessState,
                onFailure = { CoinListState.Error }
            )
            _state.update { newState }
        }
    }

    private fun createSuccessState(coinList: List<Coin>): CoinListState.Content {
        return CoinListState.Content(
            items = coinList.toImmutableList()
        )
    }
}