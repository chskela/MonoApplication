package com.chskela.monoapplication.domain.reports.models

import com.chskela.monoapplication.domain.category.models.TypeCategory

data class TransactionWithCategory(
    val id: Long,
    val timestamp: Long,
    val amount: Long,
    val note: String,
    val categoryId: Long,
    val name: String,
    val icon: String,
    val type: TypeCategory,
)
