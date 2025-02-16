package com.example.exrate.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.feature.coin.navigation.CoinDestination
import com.example.feature.coin.navigation.SelectCurrencyResult
import com.example.feature.coin.navigation.coinDestination
import com.example.feature.selectcurrency.navigation.SelectCurrencyDestination
import com.example.feature.selectcurrency.navigation.selectCurrencyDestination
import com.example.feature.settings.navigation.SettingsDestination
import com.example.feature.settings.navigation.settingsDestination

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CoinDestination
    ) {
        coinDestination(
            navController = navController,
            onSettingsClicked = { navController.navigate(SettingsDestination) }
        )
        settingsDestination(
            onBack = { navController.popBackStack() },
            onCurrencyClicked = { navController.navigate(SelectCurrencyDestination) }
        )
        selectCurrencyDestination(
            onBack = { navController.popBackStack() },
            onCurrencySelected = { navController.onCurrencySelected() }
        )
    }
}

private fun NavController.onCurrencySelected() {
    val result = SelectCurrencyResult.NEW_CURRENCY_SELECTED
    val destination = getBackStackEntry<CoinDestination>()
    destination.savedStateHandle[SelectCurrencyResult.KEY] = result
    popBackStack()
}