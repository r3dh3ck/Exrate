package com.example.feature.coin

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.data.CoinRepositoryImpl
import com.example.feature.coin.domain.CoinPaginationUseCaseImpl
import com.example.feature.coin.ui.main.MainEffect
import com.example.feature.coin.ui.main.MainViewModel
import com.example.feature.coin.ui.main.coinlist.CoinListState
import com.example.feature.currency.Currency
import com.example.network.CoingeckoApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainTest {

    private val api = mockk<CoingeckoApi>()
    private val dataStore = mockk<CurrencyDataStore>()
    private val repository = CoinRepositoryImpl(api, dataStore)
    private val useCase = CoinPaginationUseCaseImpl(repository)
    private val viewModel = MainViewModel(useCase)

    @Test
    fun loadMoreAndRefresh() = runTest {
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = pageSize,
                page = 1
            )
        } returns coinResponseList
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = pageSize,
                page = 2
            )
        } returns coinResponseList2Page
        viewModel.state.test {
            assertEquals(CoinListState.Loading, awaitItem())
            val successState = CoinListState.Content(
                items = coinList,
                loadMore = CoinListState.LoadState.NotLoaded,
                refresh = CoinListState.LoadState.NotLoaded
            )
            assertEquals(successState, awaitItem())
            viewModel.loadMore()
            val loadMoreLoadingState = successState.copy(
                loadMore = CoinListState.LoadState.Loading
            )
            assertEquals(loadMoreLoadingState, awaitItem())
            val loadMoreSuccessState = loadMoreLoadingState.copy(
                items = coinList2Pages,
                loadMore = CoinListState.LoadState.NotLoaded
            )
            assertEquals(loadMoreSuccessState, awaitItem())
            viewModel.refresh()
            val refreshLoadingState = loadMoreSuccessState.copy(
                refresh = CoinListState.LoadState.Loading
            )
            assertEquals(refreshLoadingState, awaitItem())
            val refreshSuccessState = refreshLoadingState.copy(
                items = coinList,
                refresh = CoinListState.LoadState.NotLoaded
            )
            assertEquals(refreshSuccessState, awaitItem())
        }
    }

    @Test
    fun loadMoreError() = runTest {
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = pageSize,
                page = 1
            )
        } returns coinResponseList
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = pageSize,
                page = 2
            )
        } throws Throwable()
        viewModel.state.test {
            assertEquals(CoinListState.Loading, awaitItem())
            val successState = CoinListState.Content(
                items = coinList,
                loadMore = CoinListState.LoadState.NotLoaded,
                refresh = CoinListState.LoadState.NotLoaded
            )
            assertEquals(successState, awaitItem())
            viewModel.loadMore()
            val loadMoreLoadingState = successState.copy(
                loadMore = CoinListState.LoadState.Loading
            )
            assertEquals(loadMoreLoadingState, awaitItem())
            val loadMoreErrorState = loadMoreLoadingState.copy(
                loadMore = CoinListState.LoadState.Error
            )
            assertEquals(loadMoreErrorState, awaitItem())
        }
    }

    @Test
    fun refreshError() = runTest {
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = pageSize,
                page = 1
            )
        } returns coinResponseList andThenThrows Throwable()
        turbineScope {
            val stateFlow = viewModel.state.testIn(backgroundScope)
            val effectFlow = viewModel.effect.testIn(backgroundScope)
            assertEquals(CoinListState.Loading, stateFlow.awaitItem())
            val successState = CoinListState.Content(
                items = coinList,
                loadMore = CoinListState.LoadState.NotLoaded,
                refresh = CoinListState.LoadState.NotLoaded
            )
            assertEquals(successState, stateFlow.awaitItem())
            viewModel.refresh()
            val refreshLoadingState = successState.copy(
                refresh = CoinListState.LoadState.Loading
            )
            assertEquals(refreshLoadingState, stateFlow.awaitItem())
            val refreshErrorState = refreshLoadingState.copy(
                refresh = CoinListState.LoadState.Error
            )
            assertEquals(refreshErrorState, stateFlow.awaitItem())
            assertEquals(MainEffect.ShowSnackbar, effectFlow.awaitItem())
        }
    }

    @Test
    fun retry() = runTest {
        coEvery { api.getMarkets(any(), any(), any()) } throws Throwable()
        viewModel.state.test {
            assertEquals(CoinListState.Loading, awaitItem())
            assertEquals(CoinListState.Error, awaitItem())
            viewModel.retry()
            assertEquals(CoinListState.Loading, awaitItem())
            assertEquals(CoinListState.Error, awaitItem())
        }
    }
}