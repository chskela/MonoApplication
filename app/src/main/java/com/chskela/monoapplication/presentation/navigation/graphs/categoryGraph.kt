package com.chskela.monoapplication.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chskela.monoapplication.presentation.navigation.MonoScreens
import com.chskela.monoapplication.presentation.screens.category.CategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.edit_category.AddCategoryActivityScreen
import com.chskela.monoapplication.presentation.screens.edit_category.EditCategoryActivityScreen

fun NavGraphBuilder.categoryGraph(navController: NavController) {
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