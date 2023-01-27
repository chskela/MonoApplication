package com.chskela.monoapplication.data.transaction.repository

import com.chskela.monoapplication.data.transaction.storage.dao.TransactionDao
import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import com.chskela.monoapplication.mappers.mapToTransactionEntity
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) :
    TransactionRepository, AbstractRepository() {

    override suspend fun getTransactionById(id: Long): TransactionEntityWithCategory =
        withContext(coroutineContext) {
            transactionDao.getTransactionById(id)
        }

    override suspend fun insertTransaction(transaction: Transaction) {
        withContext(coroutineContext) {
            transactionDao.insertTransaction(transaction.mapToTransactionEntity())
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        withContext(coroutineContext) {
            transactionDao.updateTransaction(transaction.mapToTransactionEntity())
        }
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        withContext(coroutineContext) {
            transactionDao.deleteTransaction(transaction.mapToTransactionEntity())
        }
    }

    override suspend fun deleteAllTransactions() {
        withContext(coroutineContext) {
            transactionDao.deleteAllTransactions()
        }
    }
}