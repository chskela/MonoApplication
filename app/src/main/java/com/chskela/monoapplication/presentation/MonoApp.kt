package com.chskela.monoapplication.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chskela.monoapplication.presentation.navigation.BottomMenuScreens
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.navigation.graphs.categoryGraph
import com.chskela.monoapplication.presentation.navigation.graphs.reportGraph
import com.chskela.monoapplication.presentation.navigation.graphs.settingGraph
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingScreen
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingViewModel
import com.chskela.monoapplication.presentation.screens.transaction.TransactionScreen
import com.chskela.monoapplication.presentation.screens.transaction.TransitionViewModel
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MonoBottomNavigation
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonoApp(onBoardingIsSkip: Boolean) {
    MonoApplicationTheme {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

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

        Scaffold(
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
            containerColor = MaterialTheme.colorScheme.surface
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = startDestination
            ) {

                composable(MonoScreens.OnBoarding.name) {
                    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        uiState = onBoardingViewModel.uiState,
                        onEvent = onBoardingViewModel::onEvent,
                        onMainScreen = {
                            navController.popBackStack()
                            navController.navigate(MonoScreens.Transaction.name)
                        }
                    )
                }

                composable(MonoScreens.Transaction.name) {
                    val transitionViewModel: TransitionViewModel = hiltViewModel()
                    val uiState by transitionViewModel.uiState.collectAsState()
                    TransactionScreen(
                        uiState = uiState,
                        onEvent = transitionViewModel::onEvent,
                    )
                }

                reportGraph(navController)

                settingGraph(navController)

                categoryGraph(navController)
            }
        }
    }
}