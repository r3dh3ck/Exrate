package com.example.exrate.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.exrate.di.ViewModelFactory
import com.example.exrate.domain.Currency
import com.example.exrate.ui.details.DetailsScreen
import com.example.exrate.ui.main.MainScreen

@Composable
fun App(
    viewModelFactory: ViewModelFactory
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {
            MainScreen(
                viewModel = viewModel(factory = viewModelFactory),
                onCurrencyClicked = remember { navController::openCurrencyDetails }
            )
        }
        composable<Screen.Details> { backStackEntry ->
            val route = backStackEntry.toRoute<Screen.Details>()
            DetailsScreen(
                viewModel = viewModel(factory = viewModelFactory),
                currencyId = route.currencyId,
                onBack = remember { { navController.popBackStack() } }
            )
        }
    }
}

private fun NavController.openCurrencyDetails(currency: Currency) {
    val route = Screen.Details(currency.id)
    navigate(route)
}