package com.example.exrate

import app.cash.turbine.test
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.currency.Currency
import com.example.feature.currency.CurrencyRepositoryImpl
import com.example.feature.selectcurrency.ui.CurrencyListState
import com.example.feature.selectcurrency.ui.SelectCurrencyState
import com.example.feature.selectcurrency.ui.SelectCurrencyViewModel
import com.example.network.CoingeckoApi
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SelectCurrencyTest {

    val api = mockk<CoingeckoApi>()
    val dataStore = mockk<CurrencyDataStore>()
    val repository = CurrencyRepositoryImpl(api, dataStore)
    val viewModel = SelectCurrencyViewModel(repository)
    val initState = SelectCurrencyState(
        query = "",
        currencyList = CurrencyListState.Loading
    )

    @Test
    fun query() = runTest {
        coEvery { api.getSupportedCurrencies() } returns currencyResponseList
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            viewModel.load()
            var state = initState.copy(
                currencyList = CurrencyListState.Content(currencyListUsdSelected)
            )
            assertEquals(state, awaitItem())
            viewModel.query("eur")
            state = state.copy(query = "eur")
            assertEquals(state, awaitItem())
            state = state.copy(
                currencyList = CurrencyListState.Content(currencyListEurQueried)
            )
            assertEquals(state, awaitItem())
        }
    }

    @Test
    fun selectCurrency() = runTest {
        val currencySlot = slot<Currency>()
        coEvery { dataStore.setSelectedCurrency(capture(currencySlot)) } returns Unit
        testViewModel {
            viewModel.selectCurrency(eurCurrency)
            assertEquals(eurCurrency, currencySlot.captured)
        }
    }

    @Test
    fun error() = runTest {
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            viewModel.load()
            val errorState = initState.copy(
                currencyList = CurrencyListState.Error
            )
            assertEquals(errorState, awaitItem())
        }
    }

    private fun TestScope.testViewModel(body: () -> Unit) {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)
        body.invoke()
        Dispatchers.resetMain()
    }
}