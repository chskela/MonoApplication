package com.chskela.monoapplication.presentation.screens.edit_category

import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

sealed class EditCategoryEvent{
    data class GetCategory(val categoryId: Long) : EditCategoryEvent()
    data class EditCategory(val categoryUi: CategoryUi) : EditCategoryEvent()
    data class ChangeCategoryName(val value: String): EditCategoryEvent()
    data class ChangeCategoryIcon(val iconId: Long): EditCategoryEvent()
    object AddCategory : EditCategoryEvent()
}
