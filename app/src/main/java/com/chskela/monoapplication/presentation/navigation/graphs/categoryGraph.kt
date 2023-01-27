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
import com.chskela.monoapplication.presentation.screens.add_edit_category.AddAndEditCategoryScreen
import com.chskela.monoapplication.presentation.screens.category.CategoryScreen
import com.chskela.monoapplication.presentation.screens.category.CategoryViewModel

fun NavGraphBuilder.categoryGraph(navController: NavController) {
    navigation(
        startDestination = MonoScreens.Category.name,
        route = MonoScreens.CategoryRoot.name
    ) {

        composable(MonoScreens.Category.name) {
            val categoryViewModel: CategoryViewModel = hiltViewModel()
            CategoryScreen(
                uiState = categoryViewModel.uiState.value,
                onEvent = categoryViewModel::onEvent,
                onBack = { navController.navigateUp() },
                onEdit = { id ->
                    navController
                        .navigate("${MonoScreens.AddEditCategory.name}?categoryId=${id}")
                },
                onAddMore = { navController.navigate(MonoScreens.AddEditCategory.name) }
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

            AddAndEditCategoryScreen(
                uiState = addEditCategoryViewModel.uiState,
                onEvent = addEditCategoryViewModel::onEvent,
                onBack = { navController.navigateUp() })
        }
    }
}