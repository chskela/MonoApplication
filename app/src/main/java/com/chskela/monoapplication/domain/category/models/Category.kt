package com.chskela.monoapplication.domain.category.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Category(
    val id: Long,
    val name: String,
    val icon: String,
    val type: TypeCategory = TypeCategory.Expense,
)

@Parcelize
enum class TypeCategory : Parcelable {
    Expense, Income
}