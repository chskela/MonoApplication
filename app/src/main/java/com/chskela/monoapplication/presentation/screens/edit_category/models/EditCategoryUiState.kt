package com.chskela.monoapplication.presentation.screens.edit_category.models

import androidx.annotation.DrawableRes
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class EditCategoryUiState(
    val categoryName: String = "",
    @DrawableRes val icon: Int = 0,
    val icons: List<CategoryUi> = emptyList(),
)
