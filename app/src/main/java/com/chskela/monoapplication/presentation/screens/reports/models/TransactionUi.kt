package com.chskela.monoapplication.presentation.screens.reports.models

data class TransactionUi(
    val id: Long,
    val timestamp: Long,
    val amount: Double,
    val note: String,
    val type: TypeTransaction = TypeTransaction.Expense,
    val category: String,
    val icon: String?
)

enum class TypeTransaction {
    Expense,
    Income
}