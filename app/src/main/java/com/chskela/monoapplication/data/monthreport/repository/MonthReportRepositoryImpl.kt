package com.chskela.monoapplication.data.monthreport.repository

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.data.monthreport.storage.dao.MonthReportDao
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.monthreport.models.TransactionWithCategory
import com.chskela.monoapplication.domain.monthreport.repository.MonthReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MonthReportRepositoryImpl(private val monthReportDao: MonthReportDao) :
    MonthReportRepository {

    override fun getAllTransactions(): Flow<List<TransactionWithCategory>> {
        return monthReportDao.getAllTransactions().map { list ->
            list.map { mapToDomain(it) }
        }
    }

    private fun mapTypeToDomain(type: Type): TypeCategory = when (type) {
        Type.Expense -> TypeCategory.Expense
        Type.Income -> TypeCategory.Income
    }

    private fun mapToDomain(transactionWithCategory: TransactionEntityWithCategory): TransactionWithCategory =
        TransactionWithCategory(
            id = transactionWithCategory.id ?: 0,
            timestamp = transactionWithCategory.timestamp,
            amount = transactionWithCategory.amount,
            note = transactionWithCategory.note,
            name = transactionWithCategory.name,
            icon = transactionWithCategory.icon,
            type = mapTypeToDomain(transactionWithCategory.type)
        )

}
