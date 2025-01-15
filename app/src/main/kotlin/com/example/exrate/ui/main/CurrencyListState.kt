package com.example.exrate.ui.main

import androidx.compose.runtime.Immutable
import com.example.exrate.domain.Currency
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface CurrencyListState {

    data object Loading : CurrencyListState

    data object Error : CurrencyListState

    @JvmInline
    value class Content(
        val items: ImmutableList<Currency>
    ) : CurrencyListState
}