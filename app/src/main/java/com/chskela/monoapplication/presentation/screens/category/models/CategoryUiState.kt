package com.chskela.monoapplication.presentation.screens.category.models

import com.chskela.monoapplication.presentation.ui.components.monocategorysurface.CategoryUi

data class CategoryUiState(
    val expenseList: List<CategoryUi> = emptyList(),
    val incomeList: List<CategoryUi> = emptyList()
)
