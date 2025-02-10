package com.example.feature.coin.navigation

enum class SelectCurrencyResult {
    EMPTY,
    NEW_CURRENCY_SELECTED;

    companion object {
        const val KEY: String = "select_currency_result_key"
    }
}