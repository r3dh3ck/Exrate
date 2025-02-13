package com.example.feature.coin.ui.main.coinlist

import androidx.compose.runtime.Immutable
import com.example.feature.coin.ui.main.coinlist.item.CoinItem
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal sealed interface CoinListState {

    data object Loading : CoinListState

    data object Error : CoinListState

    data class Content(
        val items: ImmutableList<CoinItem>,
        val loadMore: LoadState,
        val refresh: LoadState
    ) : CoinListState

    sealed interface LoadState {

        data object NotLoaded : LoadState

        data object Loading : LoadState

        data object Error : LoadState
    }

    fun mapContent(
        mapper: Content.() -> Content
    ): CoinListState {
        return when (this) {
            is Loading -> Loading
            is Error -> Error
            is Content -> mapper(this)
        }
    }
}