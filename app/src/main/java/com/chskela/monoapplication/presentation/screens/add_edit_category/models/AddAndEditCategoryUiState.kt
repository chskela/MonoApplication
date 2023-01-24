package com.chskela.monoapplication.presentation.screens.add_edit_category.models

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class AddAndEditCategoryUiState(
    val categoryId: Long = 0,
    val currentTab: Int = 0,
    val categoryName: String = "",
    val currentIcon: String = "",
    val typeCategory: TypeCategory = TypeCategory.Expense,
    val icons: List<CategoryUi> = emptyList(),
    val isNewCategory: Boolean = true
)
