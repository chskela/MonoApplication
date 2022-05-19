package com.chskela.monoapplication.presentation.navigation.graphs

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.details.CategoryReportDetailsScreen
import com.chskela.monoapplication.presentation.screens.details.CategoryReportDetailsViewModels
import com.chskela.monoapplication.presentation.screens.reports.ReportsScreen
import com.chskela.monoapplication.presentation.screens.reports.ReportsViewModels

fun NavGraphBuilder.reportGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Reports.name,
        route = MonoScreens.ReportRoot.name
    ) {

        composable(MonoScreens.Reports.name) {
            val reportViewModels: ReportsViewModels = hiltViewModel()
            ReportsScreen(
                uiState = reportViewModels.uiState.value,
                onEvent = reportViewModels::onEvent,
                onSelectCategory = { id ->
                    navController
                        .navigate("${MonoScreens.CategoryReportDetails.name}?categoryId=${id}")
                }
            )
        }

        composable(
            route = "${MonoScreens.CategoryReportDetails.name}?categoryId={categoryId}",
            arguments = listOf(navArgument(name = "categoryId") {
                type = NavType.LongType
                defaultValue = -1
            })

        ) {
            val categoryReportDetailsViewModels: CategoryReportDetailsViewModels = hiltViewModel()
            CategoryReportDetailsScreen(
                uiState = categoryReportDetailsViewModels.uiState.value,
                onEvent = categoryReportDetailsViewModels::onEvent,
                onBack = { navController.navigateUp() })

        }
    }
}