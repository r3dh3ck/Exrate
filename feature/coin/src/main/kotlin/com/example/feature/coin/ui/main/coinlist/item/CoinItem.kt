package com.example.feature.coin.ui.main.coinlist.item

data class CoinItem(
    val id: String,
    val imageUrl: String,
    val symbol: String,
    val name: String,
    val price: String
) {
    companion object {
        fun newInstance(
            id: String = "",
            imageUrl: String = "",
            symbol: String = "",
            name: String = "",
            price: String = ""
        ): CoinItem {
            return CoinItem(
                id = id,
                imageUrl = imageUrl,
                symbol = symbol,
                name = name,
                price = price
            )
        }
    }
}