package com.example.feature.selectcurrency.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.selectcurrency.ui.SelectCurrencyScreen

fun NavGraphBuilder.selectCurrencyDestination(
    onBack: () -> Unit
) {
    composable<SelectCurrencyDestination> {
        SelectCurrencyScreen(onBack)
    }
}