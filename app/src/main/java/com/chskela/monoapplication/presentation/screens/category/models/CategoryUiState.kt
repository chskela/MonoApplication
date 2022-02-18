package com.chskela.monoapplication.presentation.screens.category.models

data class CategoryUiState(
    val expenseList: List<CategoryUi> = emptyList(),
    val incomeList: List<CategoryUi> = emptyList()
)
