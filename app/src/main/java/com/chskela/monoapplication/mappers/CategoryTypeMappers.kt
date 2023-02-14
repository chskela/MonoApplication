package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.domain.category.models.TypeCategory

fun TypeCategory.mapToData(): Type = when (this) {
    TypeCategory.Expense -> Type.Expense
    TypeCategory.Income -> Type.Income
}

fun Type.mapToDomain(): TypeCategory = when (this) {
    Type.Expense -> TypeCategory.Expense
    Type.Income -> TypeCategory.Income
}