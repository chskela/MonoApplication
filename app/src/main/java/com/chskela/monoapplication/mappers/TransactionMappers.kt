package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import com.chskela.monoapplication.domain.transaction.models.Transaction

fun Transaction.mapToTransactionEntity(): TransactionEntity =
    TransactionEntity(
        timestamp = timestamp,
        amount = amount,
        note = note,
        categoryId = categoryId,
    )

fun TransactionEntity.mapToTransaction(): Transaction =
    Transaction(
        id = id ?: 0,
        timestamp = timestamp,
        amount = amount,
        note = note,
        categoryId = categoryId,
    )

//    private fun mapTransactionToDomain(transactionEntityWithCategory: TransactionEntityWithCategory): Transaction =
//        Transaction(
//            id = transactionEntity.id ?: 0,
//            timestamp = transactionEntity.timestamp,
//            amount = transactionEntity.amount,
//            note = transactionEntity.note,
//            categoryId = transactionEntity.categoryId,
//            currencyId = transactionEntity.currencyId
//        )