package com.chskela.monoapplication.data.transaction.storage.models

import androidx.room.PrimaryKey
import com.chskela.monoapplication.data.category.storage.models.Type

data class TransactionEntityWithCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val timestamp: Long,
    val amount: Long,
    val note: String,
    val name: String,
    val icon: Int,
    val type: Type = Type.Expense,
)
