package com.chskela.monoapplication.data.category.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val icon: String,

    @ColumnInfo
    val type: Type = Type.Expense,
)

enum class Type {
    Expense, Income
}