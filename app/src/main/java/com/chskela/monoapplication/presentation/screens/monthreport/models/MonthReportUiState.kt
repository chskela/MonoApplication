package com.chskela.monoapplication.presentation.screens.monthreport.models

data class MonthReportUiState(
    val currentData: String = "February, 2022",
    val currency: String = "",
    val currentBalance: Double = 0.0,
    val income: Double = 0.0,
    val expense: Double = 0.0,
    val expenseIncome: Double = 0.0,
    val previousBalance: Double = 0.0,
    val currentTab: Int = 0,
    val transactionList: List<TransactionUi> = emptyList()
)
