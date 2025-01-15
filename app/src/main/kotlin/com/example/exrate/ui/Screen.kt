package com.example.exrate.ui

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data class Details(
        val currencyId: String
    ) : Screen
}