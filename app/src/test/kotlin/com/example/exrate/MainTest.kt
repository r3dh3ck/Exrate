package com.example.exrate

import app.cash.turbine.test
import com.example.exrate.data.CoingeckoApi
import com.example.exrate.data.CurrencyRepositoryImpl
import com.example.exrate.ui.main.CurrencyListState
import com.example.exrate.ui.main.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MainTest {

    private val api = mockk<CoingeckoApi>()
    private val repository = CurrencyRepositoryImpl(api)
    private val viewModel = MainViewModel(repository)

    @Test
    fun success() = runTest {
        coEvery { api.getMarkets() } returns currencyResponseList
        val successState = CurrencyListState.Content(currencyList)
        viewModel.state.test {
            assertEquals(CurrencyListState.Loading, awaitItem())
            viewModel.getCurrencyList()
            assertEquals(successState, awaitItem())
        }
    }

    @Test
    fun error() = runTest {
        coEvery { api.getMarkets() } throws Throwable()
        viewModel.state.test {
            assertEquals(CurrencyListState.Loading, awaitItem())
            viewModel.getCurrencyList()
            assertEquals(CurrencyListState.Error, awaitItem())
        }
    }
}