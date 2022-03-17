package com.chskela.monoapplication.domain.transaction.repository

import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions(): Flow<List<TransactionEntityWithCategory>>

    suspend fun getTransactionById(id: Long): TransactionEntityWithCategory

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)
}