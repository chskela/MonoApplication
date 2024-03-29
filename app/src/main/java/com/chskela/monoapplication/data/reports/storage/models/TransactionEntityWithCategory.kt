package com.chskela.monoapplication.data.reports.storage.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.chskela.monoapplication.data.category.storage.models.Type
import java.util.*

data class TransactionEntityWithCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timestamp: Date,
    val amount: Long,
    val note: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
    val name: String,
    val icon: String,
    val type: Type = Type.Expense,
)
