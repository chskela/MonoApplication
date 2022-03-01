package com.chskela.monoapplication.data.transaction.repository

import com.chskela.monoapplication.data.transaction.storage.dao.TransactionDao
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionById(id: Long): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(mapCategoryToStorage(transaction))
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    private fun mapTransactionToDomain(transactionEntity: TransactionEntity): Transaction = TODO()

    private fun mapCategoryToStorage(transaction: Transaction): TransactionEntity = TransactionEntity(
        timestamp = transaction.timestamp,
        amount = transaction.amount,
        note = transaction.note,
        categoryId = transaction.categoryId,
        currencyId = transaction.currencyId
    )
}