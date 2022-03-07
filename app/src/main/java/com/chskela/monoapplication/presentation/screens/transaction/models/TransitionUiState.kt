package com.chskela.monoapplication.presentation.screens.transaction.models

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class TransitionUiState(
    val currentTab: Int = 0,
    val currentData: String = "Feb 24, 2022 (Sat)",
    val amount: String = "",
    val note: String = "",
    val enabledButton: Boolean = false,
    val listCategory: List<CategoryUi> = emptyList(),
    val currentCategory: Long = 0,
    val currentCurrency: Currency,
)
