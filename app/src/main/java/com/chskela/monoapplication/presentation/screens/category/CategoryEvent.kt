package com.chskela.monoapplication.presentation.screens.category

import com.chskela.monoapplication.presentation.ui.components.monocategorysurface.CategoryUi

sealed class CategoryEvent{
    data class EditCategory(val categoryUi: CategoryUi) : CategoryEvent()
    object AddMore : CategoryEvent()
}
