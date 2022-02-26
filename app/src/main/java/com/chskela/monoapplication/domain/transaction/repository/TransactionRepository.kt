package com.chskela.monoapplication.domain.transaction.repository

import com.chskela.monoapplication.domain.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions(): Flow<List<Transaction>>

    suspend fun getTransactionById(id: Long): Transaction

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)
}