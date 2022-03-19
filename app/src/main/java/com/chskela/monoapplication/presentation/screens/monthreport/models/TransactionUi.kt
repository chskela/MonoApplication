package com.chskela.monoapplication.presentation.screens.monthreport.models

data class TransactionUi(
    val id: Long,
    val timestamp: Long,
    val amount: Long,
    val note: String,
    val type: TypeTransaction = TypeTransaction.Expense,
    val category: String,
    val icon: Int
)

enum class TypeTransaction {
    Expense,
    Income
}