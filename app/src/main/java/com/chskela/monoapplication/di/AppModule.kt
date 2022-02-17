package com.chskela.monoapplication.di

import android.app.Application
import androidx.room.Room
import com.chskela.monoapplication.data.currency.repository.CurrencyRepositoryImpl
import com.chskela.monoapplication.data.currency.storage.database.AppDatabase
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.domain.currency.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("database/currency_db.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(db: AppDatabase): CurrencyRepository {
        return CurrencyRepositoryImpl(db.currencyDao)
    }

    @Provides
    @Singleton
    fun provideCurrencyUseCases(repository: CurrencyRepository): CurrencyUseCases {
        return CurrencyUseCases(
            addCurrencyUseCase = AddCurrencyUseCase(repository),
            updateCurrencyUseCase = UpdateCurrencyUseCase(repository),
            deleteCurrencyUseCase = DeleteCurrencyUseCase(repository),
            getDefaultCurrencyUseCase = GetDefaultCurrencyUseCase(repository),
            getListCurrencyUseCase = GetListCurrencyUseCase(repository),
            setDefaultCurrencyUseCase = SetDefaultCurrencyUseCase(repository)
        )
    }
}