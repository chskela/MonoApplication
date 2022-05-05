package com.chskela.monoapplication.domain.reports.usecase

data class ReportsUseCases(
    val getAllTransactionsByMonthUseCase: GetAllTransactionsByMonthUseCase,
    val getCurrentBalanceUseCase: GetCurrentBalanceUseCase,
    val getExpenseUseCase: GetExpenseUseCase,
    val getIncomeUseCase: GetIncomeUseCase
)
