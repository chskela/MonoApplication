package com.chskela.monoapplication.presentation.screens.categoryreport.models

import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class CategoryReportUiState(
    val expenseList: List<CategoryUi> = emptyList(),
    val incomeList: List<CategoryUi> = emptyList()
)