package com.chskela.monoapplication.domain.transaction.models


data class Transaction(
    val id: Long,
    val timestamp: Long,
    val amount: Int,
    val note: String,
    val categoryId: Long,
    val currencyId: Long,
)
