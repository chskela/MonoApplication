package com.chskela.monoapplication.presentation.screens.category

sealed class CategoryEvent{
    data class DeleteCategory(val id: Long) : CategoryEvent()
}
