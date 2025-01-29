package com.example.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.settings.ui.SettingsScreen

fun NavGraphBuilder.settingsDestination(
    onBack: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    composable<SettingsDestination> {
        SettingsScreen(
            onBack = onBack,
            onCurrencyClicked = onCurrencyClicked
        )
    }
}