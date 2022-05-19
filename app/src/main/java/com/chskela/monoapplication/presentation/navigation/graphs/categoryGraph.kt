package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.add_edit_category.AddEditCategoryViewModel
import com.chskela.monoapplication.presentation.screens.category.CategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.add_edit_category.EditCategoryScreen

fun NavGraphBuilder.categoryGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Category.name,
        route = MonoScreens.CategoryRoot.name
    ) {

        composable(MonoScreens.Category.name) {
            CategoryActivityScreen(
                onBack = { navController.navigateUp() },
                onClick = { id: Long -> navController.navigate(MonoScreens.AddEditCategory.name + "?categoryId=${id}") },
                onAddMore = { navController.navigate(MonoScreens.AddCategory.name) }
            )
        }

        composable(
            route = "${MonoScreens.AddEditCategory.name}?categoryId={categoryId}",
            arguments = listOf(navArgument(name = "categoryId") {
                type = NavType.LongType
                defaultValue = -1
            })
        ) {
            val addEditCategoryViewModel: AddEditCategoryViewModel = hiltViewModel()

            EditCategoryScreen(
                uiState = addEditCategoryViewModel.uiState.value,
                onEvent = addEditCategoryViewModel::onEvent,
                onBack = { navController.navigateUp() })
        }
    }
}