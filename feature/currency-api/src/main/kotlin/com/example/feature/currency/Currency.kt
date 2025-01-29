package com.example.feature.currency

@JvmInline
value class Currency(
    val name: String
) {
    companion object {

        val USD: Currency = Currency("usd")
    }
}