package com.chskela.monoapplication.data.transaction.storage.dao

import androidx.room.*
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import com.chskela.monoapplication.data.monthreport.storage.models.TransactionEntityWithCategory

@Dao
interface TransactionDao {


    @Query("SELECT T.id, T.timestamp, T.amount, T.note, C.name, C.icon, C.type  FROM 'transaction' T JOIN category C ON t.category_id=c.id WHERE T.id = :id")
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
