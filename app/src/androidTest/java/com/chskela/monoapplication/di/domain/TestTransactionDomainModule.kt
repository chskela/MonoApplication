package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TestTransactionDomainModule {

    @Provides
    fun provideAddTransactionUseCase(repository: TransactionRepository): AddTransactionUseCase {
        return AddTransactionUseCase(repository)
    }

    @Provides
    fun provideDeleteAllTransactionsUseCase(repository: TransactionRepository): DeleteAllTransactionsUseCase {
        return DeleteAllTransactionsUseCase(repository)
    }
}