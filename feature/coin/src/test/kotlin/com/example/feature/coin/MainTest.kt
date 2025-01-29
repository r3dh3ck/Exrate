package com.example.feature.coin

import app.cash.turbine.test
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.data.CoinRepositoryImpl
import com.example.feature.coin.ui.main.CoinListState
import com.example.feature.coin.ui.main.MainViewModel
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
    private val viewModel = MainViewModel(repository)

    @Test
    fun success() = runTest {
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        coEvery { api.getMarkets("usd") } returns coinResponseList
        val successState = CoinListState.Content(coinList)
        viewModel.state.test {
            assertEquals(CoinListState.Loading, awaitItem())
            viewModel.getCoinList()
            assertEquals(successState, awaitItem())
        }
    }

    @Test
    fun error() = runTest {
        coEvery { api.getMarkets(any()) } throws Throwable()
        viewModel.state.test {
            assertEquals(CoinListState.Loading, awaitItem())
            viewModel.getCoinList()
            assertEquals(CoinListState.Error, awaitItem())
        }
    }
}