package com.chskela.monoapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.models.CurrencyEntity

@Database(entities = [CurrencyEntity::class, CategoryEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "mono_db"
    }
}