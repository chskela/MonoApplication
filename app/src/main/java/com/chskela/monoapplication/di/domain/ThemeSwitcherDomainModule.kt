package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.themeswitcher.repository.ThemeSwitcher
import com.chskela.monoapplication.domain.themeswitcher.usecase.IsDarkThemeUseCase
import com.chskela.monoapplication.domain.themeswitcher.usecase.SetDarkThemeUseCase
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ThemeSwitcherDomainModule {


    @Provides
    fun provideSetDarkThemeUseCase(repository: ThemeSwitcher): SetDarkThemeUseCase {
        return SetDarkThemeUseCase(repository)
    }

    @Provides
    fun provideIsDarkThemeUseCase(repository: ThemeSwitcher) : IsDarkThemeUseCase {
        return IsDarkThemeUseCase(repository)
    }
}