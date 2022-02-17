package com.chskela.monoapplication.data.currency.storage.database

import androidx.room.*
import com.chskela.monoapplication.data.currency.storage.models.CurrencyData
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getListCurrency(): Flow<List<CurrencyData>>

    @Query("SELECT * FROM currency WHERE id = :id")
    suspend fun getCurrencyById(id: Long): CurrencyData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencyData: CurrencyData)

    @Update
    suspend fun updateCurrency(currencyData: CurrencyData)

    @Delete
    suspend fun deleteCurrency(currencyData: CurrencyData)
}