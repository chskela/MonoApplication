package com.chskela.monoapplication.data.monthreport.repository

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.data.monthreport.storage.dao.ReportsDao
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReportsRepositoryImpl(private val reportsDao: ReportsDao) :
    ReportsRepository {

    override fun getAllTransactionsByCategory(): Flow<List<TransactionWithCategory>> {
        return reportsDao.getAllTransactionsByCategory().map { list ->
            list.map { mapToDomain(it) }
        }
    }

    private fun mapTypeToDomain(type: Type): TypeCategory = when (type) {
        Type.Expense -> TypeCategory.Expense
        Type.Income -> TypeCategory.Income
    }

    private fun mapToDomain(transactionEntityWithCategory: TransactionEntityWithCategory): TransactionWithCategory =
        TransactionWithCategory(
            id = transactionEntityWithCategory.id ?: 0,
            timestamp = transactionEntityWithCategory.timestamp,
            amount = transactionEntityWithCategory.amount,
            note = transactionEntityWithCategory.note,
            categoryId = transactionEntityWithCategory.categoryId,
            name = transactionEntityWithCategory.name,
            icon = transactionEntityWithCategory.icon,
            type = mapTypeToDomain(transactionEntityWithCategory.type)
        )

}
