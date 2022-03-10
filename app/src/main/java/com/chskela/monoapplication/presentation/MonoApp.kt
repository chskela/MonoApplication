package com.chskela.monoapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chskela.monoapplication.presentation.screens.monthreport.MonthReportActivityScreen
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingActivityScreen
import com.chskela.monoapplication.presentation.screens.settings.SettingsActivityScreen
import com.chskela.monoapplication.presentation.screens.transaction.TransactionActivityScreen
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MonoBottomNavigation
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@Composable
fun MonoApp() {
    MonoApplicationTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MonoScreens.fromRoute(
            backstackEntry.value?.destination?.route
        )

        NavHost(navController = navController, startDestination = MonoScreens.Transaction.name) {
            composable(MonoScreens.Transaction.name) {
                TransactionActivityScreen(
                    bottomBar = {
                        MonoBottomNavigation(
                            items = MonoScreens.values().toList(),
                            selectedItem = currentScreen
                        ) { screen -> navController.navigate(screen.name) }
                    }
                )
            }
            composable(MonoScreens.MonthReport.name) {
                MonthReportActivityScreen(bottomBar = {
                    MonoBottomNavigation(
                        items = MonoScreens.values().toList(),
                        selectedItem = currentScreen
                    ) { screen -> navController.navigate(screen.name) }
                })
            }
            composable(MonoScreens.Setting.name) {
                SettingsActivityScreen(bottomBar = {
                    MonoBottomNavigation(
                        items = MonoScreens.values().toList(),
                        selectedItem = currentScreen
                    ) { screen -> navController.navigate(screen.name) }
                })
            }
            composable(MonoScreens.OnBoarding.name) {
                OnBoardingActivityScreen()
            }
        }

    }
}

