package com.chskela.monoapplication.data.currency.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyData(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val letterCode: String,

    @ColumnInfo
    val symbol: String
)
