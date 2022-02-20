package com.chskela.monoapplication.data.currency.storage.dao

import androidx.room.*
import com.chskela.monoapplication.data.currency.storage.models.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getListCurrency(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency WHERE id = :id")
    suspend fun getCurrencyById(id: Long): CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencyEntity: CurrencyEntity)

    @Update
    suspend fun updateCurrency(currencyEntity: CurrencyEntity)

    @Delete
    suspend fun deleteCurrency(currencyEntity: CurrencyEntity)
}