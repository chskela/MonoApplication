package com.chskela.monoapplication.presentation.screens.edit_category.models

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class EditCategoryUiState(
    val id: Long = 0,
    val currentTab: Int = 0,
    val categoryName: String = "",
    val icon: String = "",
    val typeCategory: TypeCategory = TypeCategory.Expense,
    val icons: List<CategoryUi> = emptyList(),
    val isNewCategory: Boolean = true
)
