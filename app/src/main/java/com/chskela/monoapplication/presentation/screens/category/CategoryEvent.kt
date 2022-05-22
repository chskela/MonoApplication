package com.chskela.monoapplication.presentation.screens.category

sealed class CategoryEvent{
    object Restore : CategoryEvent()
    data class DeleteCategory(val id: Long) : CategoryEvent()
}
