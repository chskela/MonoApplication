package com.chskela.monoapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.chskela.monoapplication.navigation.BottomMenuScreens
import com.chskela.monoapplication.navigation.MonoScreens
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
        NavHost(navController = navController, startDestination = MonoScreens.Root.name) {
            navigation(
                startDestination = MonoScreens.OnBoarding.name,
                route = MonoScreens.Root.name
            ) {
                composable(MonoScreens.OnBoarding.name) {
                    OnBoardingActivityScreen(
                        onMainScreen = { navController.navigate(MonoScreens.Main.name) }
                    )
                }
            }

            navigation(
                startDestination = MonoScreens.Transaction.name,
                route = MonoScreens.Main.name
            ) {
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
                composable(MonoScreens.Setting.name) {
                    SettingsActivityScreen(bottomBar = {
                        MonoBottomNavigation(
                            items = menuList,
                            navController = navController
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun MainScreensNav(
    navController: NavHostController,
    menuList: List<BottomMenuScreens>,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        bottomBar = {
            MonoBottomNavigation(
                items = menuList,
                navController = navController
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        content = content
    )
}