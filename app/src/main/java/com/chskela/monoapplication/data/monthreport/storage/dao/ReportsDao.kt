package com.chskela.monoapplication.data.monthreport.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportsDao {

    @Query("SELECT T.id, T.timestamp, T.amount, T.note, C.name, C.icon, C.type  FROM 'transaction' T JOIN category C ON t.category_id=c.id")
    fun getAllTransactions(): Flow<List<TransactionEntityWithCategory>>
}