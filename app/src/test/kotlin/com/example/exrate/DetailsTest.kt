package com.example.exrate

import app.cash.turbine.test
import com.example.exrate.data.CoingeckoApi
import com.example.exrate.data.CurrencyRepositoryImpl
import com.example.exrate.ui.details.CurrencyDetailsState
import com.example.exrate.ui.details.DetailsState
import com.example.exrate.ui.details.DetailsViewModel
import com.example.exrate.ui.details.TopAppBarState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DetailsTest {

    private val api = mockk<CoingeckoApi>()
    private val repository = CurrencyRepositoryImpl(api)
    private val viewModel = DetailsViewModel(repository)
    private val initState = DetailsState(
        topAppBar = TopAppBarState.Empty,
        currencyDetails = CurrencyDetailsState.Empty
    )

    @Test
    fun success() = runTest {
        coEvery { api.getMarkets() } returns currencyResponseList
        val successState = DetailsState(
            topAppBar = TopAppBarState.Title("Ethereum"),
            currencyDetails = CurrencyDetailsState.Content(ethereumCurrency)
        )
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            repository.getCurrencyList()
            viewModel.getCurrency("eth")
            assertEquals(successState, awaitItem())
        }
    }

    @Test
    fun error() = runTest {
        val errorState = DetailsState(
            topAppBar = TopAppBarState.Empty,
            currencyDetails = CurrencyDetailsState.Error
        )
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            viewModel.getCurrency("eth")
            assertEquals(errorState, awaitItem())
        }
    }
}