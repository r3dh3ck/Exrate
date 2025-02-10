package com.example.feature.coin.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.coin.domain.Coin
import com.example.feature.coin.domain.CoinPaginationUseCase
import com.example.feature.coin.navigation.SelectCurrencyResult
import com.example.feature.coin.ui.main.coinlist.CoinListState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val useCase: CoinPaginationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.Loading)
    private val _effect = Channel<MainEffect>(capacity = Channel.BUFFERED)
    val state: StateFlow<CoinListState> = stateFlow()
    val effect: Flow<MainEffect> = _effect.receiveAsFlow()

    fun retry() {
        _state.update { CoinListState.Loading }
        viewModelScope.launch {
            val result = useCase.refresh()
            val newState = result.fold(
                onSuccess = ::handleRetrySuccess,
                onFailure = { CoinListState.Error }
            )
            _state.update { newState }
        }
    }

    fun loadMore() {
        val loadingState = _state.value.mapContent {
            copy(loadMore = CoinListState.LoadState.Loading)
        }
        _state.update { loadingState }
        viewModelScope.launch {
            val result = useCase.loadMore()
            val newState = result.fold(
                onSuccess = ::handleLoadMoreSuccess,
                onFailure = { handleLoadMoreFailure() }
            )
            _state.update { newState }
        }
    }

    fun refresh() {
        val loadingState = _state.value.mapContent {
            copy(refresh = CoinListState.LoadState.Loading)
        }
        _state.update { loadingState }
        viewModelScope.launch {
            val result = useCase.refresh()
            val newState = result.fold(
                onSuccess = ::handleRefreshSuccess,
                onFailure = { handleRefreshFailure() }
            )
            _state.update { newState }
            result.onFailure {
                _effect.send(MainEffect.ShowSnackbar)
            }
        }
    }

    fun onSelectCurrencyResult(result: SelectCurrencyResult) {
        when (result) {
            SelectCurrencyResult.NEW_CURRENCY_SELECTED -> retry()
            SelectCurrencyResult.EMPTY -> Unit
        }
    }

    private fun stateFlow(): StateFlow<CoinListState> {
        return _state.onStart { retry() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = CoinListState.Loading
            )
    }

    private fun handleRetrySuccess(coinList: List<Coin>): CoinListState {
        return CoinListState.Content(
            items = coinList.toImmutableList(),
            loadMore = CoinListState.LoadState.NotLoaded,
            refresh = CoinListState.LoadState.NotLoaded
        )
    }

    private fun handleLoadMoreSuccess(coinList: List<Coin>): CoinListState {
        return _state.value.mapContent {
            copy(
                items = coinList.toImmutableList(),
                loadMore = CoinListState.LoadState.NotLoaded
            )
        }
    }

    private fun handleLoadMoreFailure(): CoinListState {
        return _state.value.mapContent {
            copy(loadMore = CoinListState.LoadState.Error)
        }
    }

    private fun handleRefreshSuccess(coinList: List<Coin>): CoinListState {
        return _state.value.mapContent {
            copy(
                items = coinList.toImmutableList(),
                refresh = CoinListState.LoadState.NotLoaded
            )
        }
    }

    private fun handleRefreshFailure(): CoinListState {
        return _state.value.mapContent {
            copy(refresh = CoinListState.LoadState.Error)
        }
    }
}