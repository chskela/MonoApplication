package com.chskela.monoapplication.presentation.screens.reports.models

import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi

data class ReportsUiState(
    val report: Report = Report.Month,
    val isVisibleModal: Boolean = false,
    val title: Int = 0,
//    MonthReportUiState
    val currentData: String = "February, 2022",
    val currentBalance: String = "0.00",
    val income: String = "0.00",
    val expense: String = "0.00",
    val expenseIncome: String = "0.00",
    val previousBalance: String = "0.00",
    val currentTab: Int = 0,
    val transactionList: List<TransactionListUi> = emptyList(),
//    CategoryReportUiState
    val expenseList: List<CategoryUi> = emptyList(),
    val incomeList: List<CategoryUi> = emptyList()
)

enum class Report(val info: Int) {
    Month(0),
    Category(1)
}