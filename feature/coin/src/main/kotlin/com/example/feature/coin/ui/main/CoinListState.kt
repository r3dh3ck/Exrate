package com.example.feature.coin.ui.main

import androidx.compose.runtime.Immutable
import com.example.feature.coin.domain.Coin
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal sealed interface CoinListState {

    data object Loading : CoinListState

    data object Error : CoinListState

    @JvmInline
    value class Content(
        val items: ImmutableList<Coin>
    ) : CoinListState
}