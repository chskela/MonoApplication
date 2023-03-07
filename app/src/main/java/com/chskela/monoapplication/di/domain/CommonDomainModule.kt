package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.common.usecase.BeginningOfMonthUseCase
import com.chskela.monoapplication.domain.common.usecase.CurrencyFormatUseCase
import com.chskela.monoapplication.domain.common.usecase.DateFormatUseCase
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CommonDomainModule {

    @Provides
    fun provideBeginningOfMonthUseCase(): BeginningOfMonthUseCase {
        return BeginningOfMonthUseCase()
    }
    
    @Provides
    fun provideCurrencyFormatUseCase(): CurrencyFormatUseCase {
        return CurrencyFormatUseCase()
    }

    @Provides
    fun provideDateFormatUseCase(): DateFormatUseCase {
        return DateFormatUseCase()
    }
}