package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.currency.CurrencyActivityScreen
import com.chskela.monoapplication.presentation.screens.settings.SettingsActivityScreen

fun NavGraphBuilder.settingGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Setting.name,
        route = MonoScreens.SettingRoot.name
    ) {

        composable(MonoScreens.Setting.name) {
            SettingsActivityScreen(
                onCategory = { navController.navigate(MonoScreens.Category.name) },
                onCurrency = { navController.navigate(MonoScreens.Currency.name) }
            )
        }

        composable(MonoScreens.Currency.name) {
            CurrencyActivityScreen(onBack = { navController.navigateUp() })
        }
    }
}