package com.example.feature.coin.ui.main.coinlist

import com.example.feature.coin.domain.Coin

internal sealed interface CoinListAction {

    data object Retry : CoinListAction

    data object LoadMore : CoinListAction

    data object RetryLoadMore : CoinListAction

    data object Refresh : CoinListAction

    @JvmInline
    value class CoinClicked(
        val coin: Coin
    ) : CoinListAction
}