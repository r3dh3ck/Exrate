package com.example.exrate

import app.cash.turbine.test
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.currency.Currency
import com.example.feature.currency.CurrencyRepositoryImpl
import com.example.feature.settings.ui.SettingsState
import com.example.feature.settings.ui.SettingsViewModel
import com.example.network.CoingeckoApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class SettingsTest {

    val api = mockk<CoingeckoApi>()
    val dataStore = mockk<CurrencyDataStore>()
    val repository = CurrencyRepositoryImpl(api, dataStore)
    val viewModel = SettingsViewModel(repository)

    @Test
    fun test() = runTest {
        val eurCurrency = Currency("eur")
        coEvery { dataStore.getSelectedCurrency() } returns eurCurrency
        val initState = SettingsState(Currency.USD)
        val updatedState = SettingsState(eurCurrency)
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            viewModel.load()
            assertEquals(updatedState, awaitItem())
        }
    }
}