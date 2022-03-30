package com.chskela.monoapplication.domain.transaction.repository

import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.transaction.models.Transaction

interface TransactionRepository {

    suspend fun getTransactionById(id: Long): TransactionEntityWithCategory

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun updateTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    suspend fun deleteAllTransactions()
}