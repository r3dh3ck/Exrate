package com.example.feature.coin.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.feature.coin.domain.Coin
import com.example.feature.coin.ui.details.DetailsScreen
import com.example.feature.coin.ui.main.MainScreen

fun NavGraphBuilder.coinDestination(
    navController: NavHostController,
    onSettingsClicked: () -> Unit
) {
    navigation<CoinDestination>(
        startDestination = CoinDestination.MainScreen
    ) {
        composable<CoinDestination.MainScreen> {
            MainScreen(
                onCoinClicked = remember { navController::openCoinDetails },
                onSettingsClicked = onSettingsClicked,
            )
        }
        composable<CoinDestination.DetailsScreen> { backStackEntry ->
            val route = backStackEntry.toRoute<CoinDestination.DetailsScreen>()
            DetailsScreen(
                coinId = route.coinId,
                onBack = remember { { navController.popBackStack() } }
            )
        }
    }
}

private fun NavController.openCoinDetails(coin: Coin) {
    val route = CoinDestination.DetailsScreen(coin.id)
    navigate(route)
}