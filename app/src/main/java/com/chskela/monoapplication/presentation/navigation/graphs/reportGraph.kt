package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.details.CategoryReportDetailsActivityScreen
import com.chskela.monoapplication.presentation.screens.reports.ReportsScreen
import com.chskela.monoapplication.presentation.screens.reports.ReportsViewModels

fun NavGraphBuilder.reportGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Reports.name,
        route = MonoScreens.ReportRoot.name
    ) {

        composable(MonoScreens.Reports.name) {
            fun onClick(id: Long) {
                navController.currentBackStackEntry?.arguments?.putLong(
                    "categoryId",
                    id
                )
                navController.navigate(MonoScreens.CategoryReportDetails.name)
            }

            val reportViewModels: ReportsViewModels = hiltViewModel()
            ReportsScreen(
                uiState = reportViewModels.uiState.value,
                onEvent = reportViewModels::onEvent,
                onSelectCategory = ::onClick
            )
        }

        composable(MonoScreens.CategoryReportDetails.name) {
            val categoryId =
                navController.previousBackStackEntry?.arguments?.getLong("categoryId")
            categoryId?.let {
                CategoryReportDetailsActivityScreen(
                    onBack = { navController.navigateUp() },
                    categoryId = it
                )
            }
        }
    }
}