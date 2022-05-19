package com.chskela.monoapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.models.CurrencyEntity
import com.chskela.monoapplication.data.reports.storage.dao.ReportsDao
import com.chskela.monoapplication.data.transaction.storage.dao.TransactionDao
import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntity

@Database(
    entities = [CurrencyEntity::class, CategoryEntity::class, TransactionEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
    abstract val categoryDao: CategoryDao
    abstract val transactionDao: TransactionDao
    abstract val reportsDao: ReportsDao

    companion object {
        const val DATABASE_NAME = "mono_db"
    }
}