package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CurrencyDomainModule {

    @Provides
    fun provideGetListCurrencyUseCase(repository: CurrencyRepository): GetListCurrencyUseCase {
        return GetListCurrencyUseCase(repository)
    }

    @Provides
    fun provideSetDefaultCurrencyUseCase(repository: CurrencyRepository): SetDefaultCurrencyUseCase {
        return SetDefaultCurrencyUseCase(repository)
    }

    @Provides
    fun provideAddCurrencyUseCase(repository: CurrencyRepository): AddCurrencyUseCase {
        return AddCurrencyUseCase(repository)
    }

    @Provides
    fun provideGetDefaultCurrencyUseCase(repository: CurrencyRepository): GetDefaultCurrencyUseCase {
        return GetDefaultCurrencyUseCase(repository)
    }
}