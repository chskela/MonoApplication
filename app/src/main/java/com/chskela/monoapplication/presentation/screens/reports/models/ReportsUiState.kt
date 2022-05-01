package com.chskela.monoapplication.presentation.screens.reports.models

import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

data class ReportsUiState(
    val report: Report = Report.Month,
    val isVisibleModal: Boolean = false,
    val title: Int = 0,
//    MonthReportUiState
    val currentData: String = "February, 2022",
    val currency: String = "",
    val currentBalance: Double = 0.0,
    val income: Double = 0.0,
    val expense: Double = 0.0,
    val expenseIncome: Double = 0.0,
    val previousBalance: Double = 0.0,
    val currentTab: Int = 0,
    val transactionList: List<TransactionUi> = emptyList(),
//    CategoryReportUiState
    val expenseList: List<CategoryUi> = emptyList(),
    val incomeList: List<CategoryUi> = emptyList()
)

enum class Report(val info: String) {
    Category("Category"),
    Month("Month")
}