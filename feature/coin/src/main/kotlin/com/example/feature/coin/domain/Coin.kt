package com.example.feature.coin.domain

import com.example.feature.currency.Currency

data class Coin(
    val id: String,
    val name: String,
    val price: String,
    val marketCap: String,
    val rank: String,
    val currency: Currency
) {
    companion object {
        fun newInstance(
            id: String = "",
            name: String = "",
            price: String = "",
            marketCap: String = "",
            rank: String = "",
            currency: Currency = Currency.USD
        ): Coin {
            return Coin(
                id = id,
                name = name,
                price = price,
                marketCap = marketCap,
                rank = rank,
                currency = currency
            )
        }
    }
}