package com.chskela.monoapplication.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.chskela.monoapplication.navigation.BottomMenuScreens
import com.chskela.monoapplication.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.category.CategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.currency.CurrencyActivityScreen
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
        val systemUiController = rememberSystemUiController()
        val darkIcons = !MaterialTheme.colors.isLight

        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }

        val menuList = listOf(
            BottomMenuScreens.Transaction,
            BottomMenuScreens.MonthReport,
            BottomMenuScreens.Setting
        )
        NavHost(navController = navController, startDestination = MonoScreens.OnBoarding.name) {

            composable(MonoScreens.OnBoarding.name) {
                OnBoardingActivityScreen(
                    onMainScreen = { navController.navigate(MonoScreens.Transaction.name) {
                        popUpToTop(navController)
                    }
                    }
                )
            }

            composable(MonoScreens.Transaction.name) {
                TransactionActivityScreen(bottomBar = {
                    MonoBottomNavigation(
                        items = menuList,
                        navController = navController
                    )
                })
            }

            composable(MonoScreens.MonthReport.name) {
                MonthReportActivityScreen(bottomBar = {
                    MonoBottomNavigation(
                        items = menuList,
                        navController = navController
                    )
                })
            }

            navigation(
                startDestination = MonoScreens.Setting.name,
                route = MonoScreens.SettingRoot.name
            ) {

                composable(MonoScreens.Setting.name) {
                    SettingsActivityScreen(bottomBar = {
                        MonoBottomNavigation(
                            items = menuList,
                            navController = navController
                        )
                    },
                        onCategory = { navController.navigate(MonoScreens.Category.name) },
                        onCurrency = { navController.navigate(MonoScreens.Currency.name) }
                    )
                }

                composable(MonoScreens.Category.name) {
                    CategoryActivityScreen()
                }

                composable(MonoScreens.Currency.name) {
                    CurrencyActivityScreen()
                }
            }
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}