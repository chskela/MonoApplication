package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.domain.category.models.TypeCategory

fun TypeCategory.mapToType(): Type = when (this) {
    TypeCategory.Expense -> Type.Expense
    TypeCategory.Income -> Type.Income
}

fun Type.mapToTypeCategory(): TypeCategory = when (this) {
    Type.Expense -> TypeCategory.Expense
    Type.Income -> TypeCategory.Income
}