package com.chskela.monoapplication.data.transaction.storage.dao

import androidx.room.*
import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity

@Dao
interface TransactionDao {

    @Query(
        """
        SELECT T.id, T.timestamp, T.amount, T.note, T.category_id, C.name, C.icon, C.type  FROM 'transaction' T 
        JOIN category C ON T.category_id=C.id 
        WHERE T.id = :id
    """
    )
    suspend fun getTransactionById(id: Long): TransactionEntityWithCategory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)

    @Update
    suspend fun updateTransaction(transactionEntity: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transactionEntity: TransactionEntity)

    @Query("DELETE FROM 'transaction'")
    suspend fun deleteAllTransactions()
}
