package com.chskela.monoapplication.presentation.screens.add_edit_category

sealed class AddAndEditCategoryEvent {
    data class GetCategoryAnd(val categoryId: Long) : AddAndEditCategoryEvent()
    data class ChangeCategoryNameAnd(val value: String) : AddAndEditCategoryEvent()
    data class ChangeCategoryIconAnd(val iconId: Long) : AddAndEditCategoryEvent()
    data class SelectTab(val tab: Int) : AddAndEditCategoryEvent()
    object UpdateCategoryAnd : AddAndEditCategoryEvent()
    object AddAndCategory : AddAndEditCategoryEvent()
}
