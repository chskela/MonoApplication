package com.chskela.monoapplication.data.transaction.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val timestamp: Long,
    val amount: Long,
    val note: String,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
    @ColumnInfo(name = "currency_id")
    val currencyId: Long,
)
