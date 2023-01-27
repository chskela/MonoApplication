package com.chskela.monoapplication.presentation.screens.add_edit_category

sealed class AddAndEditCategoryEvent {
    data class GetCategory(val categoryId: Long) : AddAndEditCategoryEvent()
    data class ChangeCategoryName(val value: String) : AddAndEditCategoryEvent()
    data class ChangeCategoryIcon(val iconId: Long) : AddAndEditCategoryEvent()
    data class SelectTab(val tab: Int) : AddAndEditCategoryEvent()
    object UpdateCategoryAnd : AddAndEditCategoryEvent()
    object AddCategory : AddAndEditCategoryEvent()
}
