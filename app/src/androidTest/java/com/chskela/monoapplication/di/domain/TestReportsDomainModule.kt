package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.common.usecase.BeginningOfMonthUseCase
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TestReportsDomainModule {

    @Provides
    fun provideGetAllTransactionsUseCase(repository: ReportsRepository): GetAllTransactionsUseCase {
        return GetAllTransactionsUseCase(repository)
    }

    @Provides
    fun provideGetAllTransactionsByCategoryUseCase(
        repository: ReportsRepository,
        beginningOfMonthUseCase: BeginningOfMonthUseCase
    ): GetAllTransactionsOfCategoryInThisMonthUseCase {
        return GetAllTransactionsOfCategoryInThisMonthUseCase(repository, beginningOfMonthUseCase)
    }

    @Provides
    fun provideGetAmountByMonthsInCategoryMonthUseCase(
        repository: ReportsRepository,
        beginningOfMonthUseCase: BeginningOfMonthUseCase
    ): GetAmountByMonthsInCategoryMonthUseCase {
        return GetAmountByMonthsInCategoryMonthUseCase(repository, beginningOfMonthUseCase)
    }

    @Provides
    fun provideGetAmountByCategoryPerMonthUseCase(
        repository: ReportsRepository,
        beginningOfMonthUseCase: BeginningOfMonthUseCase
    ): GetAmountByCategoryPerMonthUseCase {
        return GetAmountByCategoryPerMonthUseCase(repository, beginningOfMonthUseCase)
    }
}