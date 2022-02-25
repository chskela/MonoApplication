package com.chskela.monoapplication.data.transaction.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo
    val timestamp: Long,

    @ColumnInfo
    val amount: Int,

    @ColumnInfo
    val note: String,

)
