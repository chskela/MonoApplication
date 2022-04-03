package com.chskela.monoapplication.presentation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.chskela.monoapplication.navigation.BottomMenuScreens
import com.chskela.monoapplication.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.category.CategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.currency.CurrencyActivityScreen
import com.chskela.monoapplication.presentation.screens.edit_category.AddCategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.edit_category.EditCategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.monthreport.MonthReportActivityScreen
import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingActivityScreen
import com.chskela.monoapplication.presentation.screens.settings.SettingsActivityScreen
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
            BottomMenuScreens.MonthReport,
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
            modifier = Modifier
                .fillMaxHeight(),
            bottomBar = {
                when (currentDestination?.route) {
                    MonoScreens.Transaction.name,
                    MonoScreens.MonthReport.name,
                    MonoScreens.Setting.name -> MonoBottomNavigation(
                        items = menuList,
                        navController = navController
                    )
                    else -> {}
                }
            },
            backgroundColor = MaterialTheme.colors.surface
        ) {
            NavHost(navController = navController, startDestination = startDestination) {

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

                composable(MonoScreens.MonthReport.name) {
                    MonthReportActivityScreen()
                }

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

                navigation(
                    startDestination = MonoScreens.Category.name,
                    route = MonoScreens.CategoryRoot.name
                ) {

                    composable(MonoScreens.Category.name) {
                        fun onClick(id: Long) {
                            navController.currentBackStackEntry?.arguments?.putLong(
                                "categoryId",
                                id
                            )
                            navController.navigate(MonoScreens.EditCategory.name)
                        }
                        CategoryActivityScreen(
                            onBack = { navController.navigateUp() },
                            onClick = ::onClick,
                            onAddMore = { navController.navigate(MonoScreens.AddCategory.name) }
                        )
                    }

                    composable(MonoScreens.EditCategory.name) {
                        val categoryId =
                            navController.previousBackStackEntry?.arguments?.getLong("categoryId")
                        categoryId?.let {
                            EditCategoryActivityScreen(
                                categoryId = it,
                                onBack = { navController.navigateUp() })
                        }
                    }

                    composable(MonoScreens.AddCategory.name) {
                        AddCategoryActivityScreen(
                            onBack = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}