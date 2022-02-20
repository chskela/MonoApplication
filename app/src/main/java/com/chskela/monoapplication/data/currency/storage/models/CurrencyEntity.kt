package com.chskela.monoapplication.data.currency.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo
    val name: String,

    @ColumnInfo(name = "letter_code")
    val letterCode: String,

    @ColumnInfo
    val symbol: String,
)
