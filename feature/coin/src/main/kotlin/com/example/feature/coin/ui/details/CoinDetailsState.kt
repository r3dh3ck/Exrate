package com.example.feature.coin.ui.details

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface CoinDetailsState {

    data object Empty : CoinDetailsState

    data object Error : CoinDetailsState

    @JvmInline
    value class Content(
        val coin: CoinDetailsUiModel
    ) : CoinDetailsState
}