package com.example.feature.coin.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.coin.domain.Coin
import com.example.feature.coin.domain.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(createEmptyState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun getCoin(id: String) {
        viewModelScope.launch {
            val result = repository.getCoin(id)
            val newState = result.fold(
                onSuccess = ::createSuccessState,
                onFailure = { createErrorState() }
            )
            _state.update { newState }
        }
    }

    private fun createEmptyState(): DetailsState {
        return DetailsState(
            topAppBarTitle = "",
            coinDetails = CoinDetailsState.Empty
        )
    }

    private fun createSuccessState(coin: Coin): DetailsState {
        return DetailsState(
            topAppBarTitle = coin.name,
            coinDetails = CoinDetailsState.Content(coin)
        )
    }

    private fun createErrorState(): DetailsState {
        return DetailsState(
            topAppBarTitle = "",
            coinDetails = CoinDetailsState.Error
        )
    }
}