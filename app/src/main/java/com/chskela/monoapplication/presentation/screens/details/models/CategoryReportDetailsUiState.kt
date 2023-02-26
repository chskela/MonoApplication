package com.chskela.monoapplication.presentation.screens.details.models

import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi

data class CategoryReportDetailsUiState(
    val currentTab: Int = 0,
    val sumThisMonth: String = "0.00 $",
    val categoryName: String = "",
    val icon: String = "",
    val typeCategory: TypeCategory = TypeCategory.Expense,
    val transactionList: List<TransactionListUi> = emptyList(),
    val reportsList: List<ReportChartUi> = emptyList()
)