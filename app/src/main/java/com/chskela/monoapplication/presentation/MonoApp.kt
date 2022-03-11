package com.chskela.monoapplication.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MonoApp() {
    MonoApplicationTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = MonoScreens.fromRoute(
            backstackEntry.value?.destination?.route
        )
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight

        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }

        NavHost(navController = navController, startDestination = MonoScreens.OnBoarding.name) {
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

