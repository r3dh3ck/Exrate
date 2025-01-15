package com.example.exrate.ui.details

import androidx.compose.runtime.Immutable

@Immutable
sealed interface TopAppBarState {

    data object Empty : TopAppBarState

    @JvmInline
    value class Title(
        val value: String
    ) : TopAppBarState
}