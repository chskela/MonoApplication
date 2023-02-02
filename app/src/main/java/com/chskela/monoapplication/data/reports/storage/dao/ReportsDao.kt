package com.chskela.monoapplication.data.reports.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportsDao {

    @Query(
        """
        SELECT T.id, T.timestamp, T.amount, T.note, T.category_id, C.name, C.icon, C.type  FROM 'transaction' T 
        JOIN category C ON T.category_id=C.id
        ORDER BY T.timestamp DESC
        """
    )
    fun getAllTransactionsEntityWithCategory(): Flow<List<TransactionEntityWithCategory>>

    @Query(
        """
            SELECT T.id, T.timestamp, T.amount, T.note, T.category_id FROM 'transaction' T 
            WHERE T.category_id = :categoryId
            ORDER BY T.timestamp DESC
        """
    )
    fun getAllTransactionsByCategory(categoryId: Long): Flow<List<TransactionEntity>>

    @Query(
        """
        SELECT COALESCE(SUM(amount), 0) FROM 'transaction' T 
        WHERE T.category_id = :categoryId AND T.timestamp > :timeInMillis
        """
    )
    fun getAmountByCategoryPerMonth(categoryId: Long, timeInMillis: Long): Flow<Long>
}