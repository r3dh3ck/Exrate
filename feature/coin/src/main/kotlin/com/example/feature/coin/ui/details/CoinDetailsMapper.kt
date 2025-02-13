package com.example.feature.coin.ui.details

import com.example.feature.coin.domain.Coin
import com.example.feature.coin.ui.AmountFormatter

internal class CoinDetailsMapper {

    private val formatter = AmountFormatter()

    fun map(coin: Coin): CoinDetailsUiModel {
        return CoinDetailsUiModel(
            name = coin.name,
            rank = coin.rank,
            marketCap = "${formatter.format(coin.marketCap)} ${coin.currency.name.uppercase()}",
            price = "${formatter.format(coin.price)} ${coin.currency.name.uppercase()}"
        )
    }
}