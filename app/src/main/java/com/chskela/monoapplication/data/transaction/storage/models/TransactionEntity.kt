package com.chskela.monoapplication.data.transaction.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timestamp: Date,
    val amount: Long,
    val note: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
)
