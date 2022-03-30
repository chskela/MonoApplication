package com.chskela.monoapplication.domain.monthreport.models

import com.chskela.monoapplication.domain.category.models.TypeCategory

data class TransactionWithCategory(
    val id: Long,
    val timestamp: Long,
    val amount: Long,
    val note: String,
    val name: String,
    val icon: String,
    val type: TypeCategory,
)
