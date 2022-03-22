package com.chskela.monoapplication.presentation.screens.edit_category

import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

sealed class EditCategoryEvent{
    data class EditCategory(val categoryUi: CategoryUi) : EditCategoryEvent()
    object AddCategory : EditCategoryEvent()
}
