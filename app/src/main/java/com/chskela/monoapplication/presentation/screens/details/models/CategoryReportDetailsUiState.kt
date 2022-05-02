package com.chskela.monoapplication.presentation.screens.details.models

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.screens.details.components.ReportUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi

data class CategoryReportDetailsUiState(
    val currentTab: Int = 0,
    val currentCategory: Long = 0,
    val categoryName: String = "",
    val icon: String = "",
    val typeCategory: TypeCategory = TypeCategory.Expense,
    val currency: String = "",
    val transactionList: List<TransactionUi> = emptyList(),
    val reportsList: List<ReportUi> = emptyList()
)