package com.chskela.monoapplication.data.transaction.storage.dao

import androidx.room.*
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM 'transaction'")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM 'transaction' WHERE id = :id")
    suspend fun getTransactionById(id: Long): TransactionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)

    @Update
    suspend fun updateTransaction(transactionEntity: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transactionEntity: TransactionEntity)

}
