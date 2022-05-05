package com.chskela.monoapplication.data.monthreport.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportsDao {

    @Query("SELECT T.id, T.timestamp, T.amount, T.note, T.category_id, C.name, C.icon, C.type  FROM 'transaction' T JOIN category C ON T.category_id=C.id")
    fun getAllTransactionsByCategory(): Flow<List<TransactionEntityWithCategory>>
}