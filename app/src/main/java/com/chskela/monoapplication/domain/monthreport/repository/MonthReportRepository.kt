package com.chskela.monoapplication.domain.monthreport.repository

import com.chskela.monoapplication.domain.monthreport.models.TransactionWithCategory
import kotlinx.coroutines.flow.Flow

interface MonthReportRepository {
    fun getAllTransactions(): Flow<List<TransactionWithCategory>>
}