package com.example.exrate.domain

data class Currency(
    val id: String,
    val name: String,
    val price: String,
    val marketCap: String,
    val rank: String
) {
    companion object {
        fun newInstance(
            id: String = "",
            name: String = "",
            price: String = "",
            marketCap: String = "",
            rank: String = ""
        ): Currency {
            return Currency(
                id = id,
                name = name,
                price = price,
                marketCap = marketCap,
                rank = rank
            )
        }
    }
}