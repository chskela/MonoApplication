package com.chskela.monoapplication.presentation.screens.transaction.models

import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi

data class TransitionUiState(
    val currentTab: Int = 0,
    val currentData: String = "Feb 24, 2022 (Sat)",
    val amount: String = "",
    val note: String = "",
    val enabledButton: Boolean = false,
    val listCategory: List<CategoryUi> = emptyList(),
    val currentCategory: Long = 0,
)
