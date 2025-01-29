package com.example.feature.selectcurrency.ui

import androidx.compose.runtime.Immutable
import com.example.feature.currency.SelectableCurrency
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface CurrencyListState {

    data object Loading : CurrencyListState

    data object Error : CurrencyListState

    @JvmInline
    value class Content(
        val items: ImmutableList<SelectableCurrency>
    ) : CurrencyListState
}