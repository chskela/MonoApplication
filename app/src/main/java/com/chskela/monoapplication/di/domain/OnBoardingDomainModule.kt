package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import com.chskela.monoapplication.domain.onboarding.usecase.OnBoardingIsSkipUseCase
import com.chskela.monoapplication.domain.onboarding.usecase.SetOnBoardingIsSkipUseCase
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {


    @Provides
    fun provideSetOnBoardingIsSkipUseCase(repository: OnBoardingRepository): SetOnBoardingIsSkipUseCase {
        return SetOnBoardingIsSkipUseCase(repository)
    }

    @Provides
    fun provideOnBoardingIsSkipUseCase(repository: OnBoardingRepository) : OnBoardingIsSkipUseCase {
        return OnBoardingIsSkipUseCase(repository)
    }
}