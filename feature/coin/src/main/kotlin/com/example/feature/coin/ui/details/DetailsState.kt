package com.example.feature.coin.ui.details

import androidx.compose.runtime.Immutable

@Immutable
internal data class DetailsState(
    val topAppBarTitle: String,
    val coinDetails: CoinDetailsState
)