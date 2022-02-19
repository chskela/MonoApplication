package com.chskela.monoapplication.domain.category.models

data class Category(
    val id: Long,
    val name: String,
    val icon: Int,
    val type: TypeCategory = TypeCategory.Expense,
)

enum class TypeCategory {
    Expense, Income
}