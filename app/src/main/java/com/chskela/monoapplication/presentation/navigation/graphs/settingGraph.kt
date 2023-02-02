package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.currency.CurrencyScreen
import com.chskela.monoapplication.presentation.screens.currency.CurrencyViewModel
import com.chskela.monoapplication.presentation.screens.settings.SettingsScreen
import com.chskela.monoapplication.presentation.screens.settings.SettingsViewModel

fun NavGraphBuilder.settingGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Setting.name,
        route = MonoScreens.SettingRoot.name
    ) {

        composable(MonoScreens.Setting.name) {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                onCategory = { navController.navigate(MonoScreens.Category.name) },
                onCurrency = { navController.navigate(MonoScreens.Currency.name) },
                deleteAllData = settingsViewModel::deleteAllData
            )
        }

        composable(MonoScreens.Currency.name) {
            val currencyViewModel: CurrencyViewModel = hiltViewModel()
            CurrencyScreen(
                uiState = currencyViewModel.uiState,
                onSelectedCurrency = currencyViewModel::selectDefaultCurrency,
                onBack = { navController.navigateUp() }
            )
        }
    }
}