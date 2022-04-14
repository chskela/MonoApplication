package com.chskela.monoapplication.presentation.screens.categoryreport.details.models

import com.chskela.monoapplication.domain.category.models.TypeCategory

data class CategoryReportDetailsUiState(
    val currentTab: Int = 0,
    val currentCategory: Long = 0,
    val categoryName: String = "",
    val icon: String = "",
    val typeCategory: TypeCategory = TypeCategory.Expense,
)