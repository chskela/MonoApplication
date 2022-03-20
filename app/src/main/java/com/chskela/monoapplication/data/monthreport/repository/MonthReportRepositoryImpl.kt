package com.chskela.monoapplication.data.monthreport.repository

import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.data.monthreport.storage.dao.MonthReportDao
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.monthreport.models.TransactionWithCategory
import com.chskela.monoapplication.domain.monthreport.repository.MonthReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

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

    private fun mapToDomain(transactionEntityWithCategory: TransactionEntityWithCategory): TransactionWithCategory =
        TransactionWithCategory(
            id = transactionEntityWithCategory.id ?: 0,
            timestamp = transactionEntityWithCategory.timestamp,
            amount = transactionEntityWithCategory.amount,
            note = transactionEntityWithCategory.note,
            name = transactionEntityWithCategory.name,
            icon = transactionEntityWithCategory.icon,
            type = mapTypeToDomain(transactionEntityWithCategory.type)
        )

}
