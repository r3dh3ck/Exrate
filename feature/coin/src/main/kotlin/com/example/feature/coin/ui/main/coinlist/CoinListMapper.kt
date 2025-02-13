package com.example.feature.coin.ui.main.coinlist

import com.example.feature.coin.domain.Coin
import com.example.feature.coin.ui.AmountFormatter
import com.example.feature.coin.ui.main.coinlist.item.CoinItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

internal class CoinListMapper {

    private val formatter = AmountFormatter()

    fun map(coinList: List<Coin>): ImmutableList<CoinItem> {
        return coinList.map(::createCoinItem).toImmutableList()
    }

    private fun createCoinItem(coin: Coin): CoinItem {
        return CoinItem(
            id = coin.id,
            imageUrl = coin.imageUrl,
            symbol = coin.symbol.uppercase(),
            name = coin.name,
            price = "${formatter.format(coin.price)} ${coin.currency.name.uppercase()}"
        )
    }
}