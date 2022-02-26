package com.chskela.monoapplication.domain.transaction.repository

import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransaction(): Flow<List<TransactionEntity>>

    suspend fun getTransactionById(id: Long): TransactionEntity

    suspend fun insertTransaction(transactionEntity: TransactionEntity)

    suspend fun updateTransaction(transactionEntity: TransactionEntity)

    suspend fun deleteTransaction(transactionEntity: TransactionEntity)
}