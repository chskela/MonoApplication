package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.categoryreport.CategoryReportActivityScreen
import com.chskela.monoapplication.presentation.screens.monthreport.MonthReportActivityScreen

fun NavGraphBuilder.reportGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.MonthReport.name,
        route = MonoScreens.ReportRoot.name
    ) {

        composable(MonoScreens.MonthReport.name) {
            MonthReportActivityScreen()
        }

        composable(MonoScreens.CategoryReport.name) {
            fun onClick(id: Long) {
                navController.currentBackStackEntry?.arguments?.putLong(
                    "categoryId",
                    id
                )
                navController.navigate(MonoScreens.CategoryReportDetails.name)
            }
            CategoryReportActivityScreen(
                onSelectCategory = ::onClick
            )
        }

        composable(MonoScreens.CategoryReportDetails.name) {
            val categoryId =
                navController.previousBackStackEntry?.arguments?.getLong("categoryId")
            categoryId?.let {
                // TODO
            }
        }
    }
}