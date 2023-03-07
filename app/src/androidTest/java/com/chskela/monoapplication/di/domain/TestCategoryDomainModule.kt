package com.chskela.monoapplication.di.domain

import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TestCategoryDomainModule {

    @Provides
    fun provideUpdateCategoryUseCase(repository: CategoryRepository): UpdateCategoryUseCase {
        return UpdateCategoryUseCase(repository)
    }

    @Provides
    fun provideAddCategoryUseCase(repository: CategoryRepository): AddCategoryUseCase {
        return AddCategoryUseCase(repository)

    }

    @Provides
    fun provideDeleteCategoryUseCase(repository: CategoryRepository): DeleteCategoryUseCase {
        return DeleteCategoryUseCase(repository)

    }

    @Provides
    fun provideGetCategoryByIdUseCase(repository: CategoryRepository): GetCategoryByIdUseCase {
        return GetCategoryByIdUseCase(repository)

    }

    @Provides
    fun provideGetAllCategoryByTypeUseCase(repository: CategoryRepository): GetAllCategoryByTypeUseCase {
        return GetAllCategoryByTypeUseCase(repository)
    }

}