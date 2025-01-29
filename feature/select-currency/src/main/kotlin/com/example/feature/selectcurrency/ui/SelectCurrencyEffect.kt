package com.example.feature.selectcurrency.ui

sealed interface SelectCurrencyEffect {

    data object Back : SelectCurrencyEffect
}