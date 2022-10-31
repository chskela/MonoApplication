package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory

fun TransactionEntityWithCategory.mapToTransactionWithCategory(): TransactionWithCategory =
    TransactionWithCategory(
        id = id ?: 0,
        timestamp = timestamp,
        amount = amount,
        note = note,
        categoryId = categoryId,
        name = name,
        icon = icon,
        type = type.mapToTypeCategory()
    )