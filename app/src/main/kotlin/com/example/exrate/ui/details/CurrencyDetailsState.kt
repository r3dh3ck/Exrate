package com.example.exrate.ui.details

import androidx.compose.runtime.Immutable
import com.example.exrate.domain.Currency

@Immutable
sealed interface CurrencyDetailsState {

    data object Empty : CurrencyDetailsState

    data object Error : CurrencyDetailsState

    @JvmInline
    value class Content(
        val currency: Currency
    ) : CurrencyDetailsState
}