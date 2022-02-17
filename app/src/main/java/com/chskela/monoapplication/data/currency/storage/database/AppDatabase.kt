package com.chskela.monoapplication.data.currency.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chskela.monoapplication.data.currency.storage.models.CurrencyData

@Database(entities = [CurrencyData::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "mono_db"
    }
}