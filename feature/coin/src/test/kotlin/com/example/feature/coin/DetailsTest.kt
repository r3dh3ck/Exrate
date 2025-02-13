package com.example.feature.coin

import app.cash.turbine.test
import com.example.datastore.currency.CurrencyDataStore
import com.example.feature.coin.data.CoinRepository
import com.example.feature.coin.ui.details.CoinDetailsState
import com.example.feature.coin.ui.details.CoinDetailsUiModel
import com.example.feature.coin.ui.details.DetailsState
import com.example.feature.coin.ui.details.DetailsViewModel
import com.example.feature.currency.Currency
import com.example.network.CoingeckoApi
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DetailsTest {

    private val api = mockk<CoingeckoApi>()
    private val dataStore = mockk<CurrencyDataStore>()
    private val repository = CoinRepository(api, dataStore)
    private val viewModel = DetailsViewModel(repository)
    private val initState = DetailsState(
        topAppBarTitle = "",
        coinDetails = CoinDetailsState.Empty
    )

    @Test
    fun success() = runTest {
        coEvery { dataStore.getSelectedCurrency() } returns Currency.USD
        coEvery {
            api.getMarkets(
                vsCurrency = "usd",
                perPage = 50,
                page = 1
            )
        } returns coinResponseList
        val ethereumDetails = CoinDetailsUiModel(
            name = "Ethereum",
            rank = "2",
            marketCap = "34,000 USD",
            price = "3,400 USD"
        )
        val successState = DetailsState(
            topAppBarTitle = "Ethereum",
            coinDetails = CoinDetailsState.Content(ethereumDetails)
        )
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            repository.getCoinList(pageSize = 50, page = 1)
            viewModel.getCoin("eth")
            assertEquals(successState, awaitItem())
        }
    }

    @Test
    fun error() = runTest {
        val errorState = DetailsState(
            topAppBarTitle = "",
            coinDetails = CoinDetailsState.Error
        )
        viewModel.state.test {
            assertEquals(initState, awaitItem())
            viewModel.getCoin("eth")
            assertEquals(errorState, awaitItem())
        }
    }
}