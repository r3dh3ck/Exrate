package com.example.feature.coin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.coin.domain.CoinRepository
import com.example.feature.coin.ui.details.DetailsViewModel
import com.example.feature.coin.ui.main.MainViewModel
import javax.inject.Inject

class CoinViewModelFactory @Inject constructor(
    private val repository: CoinRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(repository) as T
            DetailsViewModel::class.java -> DetailsViewModel(repository) as T
            else -> throw IllegalStateException("")
        }
    }
}