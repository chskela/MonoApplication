package com.chskela.monoapplication.domain.transaction.models

import java.util.*

data class Transaction(
    val id: Long,
    val timestamp: Date,
    val amount: Long,
    val note: String,
    val categoryId: Long,
)
