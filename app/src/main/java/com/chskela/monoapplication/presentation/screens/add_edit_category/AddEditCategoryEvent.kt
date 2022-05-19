package com.chskela.monoapplication.presentation.screens.add_edit_category

sealed class AddEditCategoryEvent {
    data class GetCategory(val categoryId: Long) : AddEditCategoryEvent()
    data class ChangeCategoryName(val value: String) : AddEditCategoryEvent()
    data class ChangeCategoryIcon(val iconId: Long) : AddEditCategoryEvent()
    data class SelectTab(val tab: Int) : AddEditCategoryEvent()
    object UpdateCategory : AddEditCategoryEvent()
    object AddCategory : AddEditCategoryEvent()
}
