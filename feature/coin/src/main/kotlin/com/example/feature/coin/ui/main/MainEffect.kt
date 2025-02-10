package com.example.feature.coin.ui.main

internal sealed interface MainEffect {

    data object ShowSnackbar : MainEffect
}