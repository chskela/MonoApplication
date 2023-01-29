package com.chskela.monoapplication.presentation.ui.components.transactionList.model

data class TransactionListUi(
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