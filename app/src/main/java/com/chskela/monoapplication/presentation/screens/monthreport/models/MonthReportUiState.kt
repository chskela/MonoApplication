package com.chskela.monoapplication.presentation.screens.monthreport.models

import com.chskela.monoapplication.domain.transaction.models.Transaction

data class MonthReportUiState(
    val currentData: String = "Feb 24, 2022 (Sat)",
    val currentBalance: Long = 0,
    val income: Long = 0,
    val expense: Long = 0,
    val expenseIncome: Long = 0,
    val previousBalance: Long = 0,
    val currentTab: Int = 0,
    val transactionList: List<Transaction> = emptyList()
)
