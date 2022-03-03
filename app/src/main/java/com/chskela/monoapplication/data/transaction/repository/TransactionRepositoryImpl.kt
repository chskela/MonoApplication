package com.chskela.monoapplication.data.transaction.repository

import com.chskela.monoapplication.data.transaction.storage.dao.TransactionDao
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map { list ->
            list.map { mapTransactionToDomain(it) }
        }
    }

    override suspend fun getTransactionById(id: Long): Transaction {
        return mapTransactionToDomain(transactionDao.getTransactionById(id))
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(mapCategoryToStorage(transaction))
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(mapCategoryToStorage(transaction))
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(mapCategoryToStorage(transaction))
    }

    private fun mapTransactionToDomain(transactionEntity: TransactionEntity): Transaction =
        Transaction(
            id = transactionEntity.id ?: 0,
            timestamp = transactionEntity.timestamp,
            amount = transactionEntity.amount,
            note = transactionEntity.note,
            categoryId = transactionEntity.categoryId,
            currencyId = transactionEntity.currencyId
        )

    private fun mapCategoryToStorage(transaction: Transaction): TransactionEntity =
        TransactionEntity(
            timestamp = transaction.timestamp,
            amount = transaction.amount,
            note = transaction.note,
            categoryId = transaction.categoryId,
            currencyId = transaction.currencyId
        )
}