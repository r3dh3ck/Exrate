package com.example.feature.coin.ui.main.coinlist

internal sealed interface CoinListAction {

    data object Retry : CoinListAction

    data object LoadMore : CoinListAction

    data object RetryLoadMore : CoinListAction

    data object Refresh : CoinListAction

    @JvmInline
    value class CoinClicked(
        val coinId: String
    ) : CoinListAction
}