package com.example.feature.settings.ui

import androidx.compose.runtime.Immutable
import com.example.feature.currency.Currency

@Immutable
data class SettingsState(
    val selectedCurrency: Currency
)