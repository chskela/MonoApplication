package com.chskela.monoapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chskela.monoapplication.data.category.repository.CategoryRepositoryImpl
import com.chskela.monoapplication.data.currency.repository.CurrencyRepositoryImpl
import com.chskela.monoapplication.data.currency.storage.store.CurrencyStore
import com.chskela.monoapplication.data.database.AppDatabase
import com.chskela.monoapplication.data.onboarding.repository.OnBoardingRepositoryImpl
import com.chskela.monoapplication.data.onboarding.storage.store.OnBoardingStore
import com.chskela.monoapplication.data.reports.repository.ReportsRepositoryImpl
import com.chskela.monoapplication.data.transaction.repository.TransactionRepositoryImpl
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .createFromAsset("database/mono.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyStore(@ApplicationContext appContext: Context): CurrencyStore {
        return CurrencyStore(appContext)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        db: AppDatabase,
        currencyStore: CurrencyStore
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(db.currencyDao, currencyStore)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: AppDatabase): CategoryRepository {
        return CategoryRepositoryImpl(db.categoryDao)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: AppDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDao)
    }

    @Provides
    @Singleton
    fun provideMonthReportRepository(db: AppDatabase): ReportsRepository {
        return ReportsRepositoryImpl(db.reportsDao)
    }

    @Provides
    @Singleton
    fun provideOnBoardingStore(@ApplicationContext appContext: Context): OnBoardingStore {
        return OnBoardingStore(appContext)
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(onBoardingStore: OnBoardingStore): OnBoardingRepository {
        return OnBoardingRepositoryImpl(onBoardingStore)
    }
}