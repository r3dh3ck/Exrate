package com.example.feature.coin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.coin.data.CoinRepository
import com.example.feature.coin.domain.CoinPaginationUseCase
import com.example.feature.coin.ui.details.DetailsViewModel
import com.example.feature.coin.ui.main.MainViewModel
import javax.inject.Inject

internal class CoinViewModelFactory @Inject constructor(
    private val repository: CoinRepository,
    private val useCase: CoinPaginationUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(useCase) as T
            DetailsViewModel::class.java -> DetailsViewModel(repository) as T
            else -> throw IllegalStateException("")
        }
    }
}