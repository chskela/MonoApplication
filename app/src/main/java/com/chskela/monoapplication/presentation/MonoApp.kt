package com.chskela.monoapplication.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chskela.monoapplication.presentation.navigation.BottomMenuScreens
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.navigation.graphs.categoryGraph
import com.chskela.monoapplication.presentation.navigation.graphs.reportGraph
import com.chskela.monoapplication.presentation.navigation.graphs.settingGraph
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingActivityScreen
import com.chskela.monoapplication.presentation.screens.transaction.TransactionActivityScreen
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MonoBottomNavigation
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MonoApp(onBoardingIsSkip: Boolean) {
    MonoApplicationTheme {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        val color = MaterialTheme.colors.surface
        SideEffect {
            systemUiController.setSystemBarsColor(color, darkIcons = darkIcons)
        }

        val menuList = listOf(
            BottomMenuScreens.Transaction,
            BottomMenuScreens.Reports,
            BottomMenuScreens.Setting
        )

        val startDestination = if (onBoardingIsSkip) {
            MonoScreens.OnBoarding.name
        } else {
            MonoScreens.Transaction.name
        }

        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxHeight(),
            bottomBar = {
                when (currentDestination?.route) {
                    MonoScreens.Transaction.name,
                    MonoScreens.Reports.name,
                    MonoScreens.Setting.name -> MonoBottomNavigation(
                        items = menuList,
                        navController = navController
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.surface
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = startDestination
            ) {

                composable(MonoScreens.OnBoarding.name) {
                    OnBoardingActivityScreen(
                        onMainScreen = {
                            navController.popBackStack()
                            navController.navigate(MonoScreens.Transaction.name)
                        }
                    )
                }

                composable(MonoScreens.Transaction.name) {
                    TransactionActivityScreen()
                }

                reportGraph(navController)

                settingGraph(navController)

                categoryGraph(navController)
            }
        }
    }
}