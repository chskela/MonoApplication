package com.chskela.monoapplication.domain.monthreport.usecase

data class MonthReportUseCases(
    val getAllTransactionsByMonthUseCase: GetAllTransactionsByMonthUseCase,
    val getCurrentBalanceUseCase: GetCurrentBalanceUseCase,
    val getExpenseUseCase: GetExpenseUseCase,
    val getIncomeUseCase: GetIncomeUseCase
)
