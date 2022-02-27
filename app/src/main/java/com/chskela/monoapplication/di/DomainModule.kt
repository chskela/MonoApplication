package com.chskela.monoapplication.di

import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import com.chskela.monoapplication.domain.transaction.usercase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
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

    @Provides
    fun provideCategoryUseCases(repository: CategoryRepository) : CategoryUseCases {
        return CategoryUseCases(
            getAllCategoryUseCase = GetAllCategoryUseCase(repository),
            addCategoryUseCase = AddCategoryUseCase(repository),
            deleteCategoryUseCase = DeleteCategoryUseCase(repository),
            updateCategoryUseCase = UpdateCategoryUseCase(repository),
            getCategoryByIdUseCase = GetCategoryByIdUseCase(repository)
        )
    }

    @Provides
    fun provideTransactionUseCases(repository: TransactionRepository) : TransactionUseCases {
        return TransactionUseCases(
            addTransactionUseCase = AddTransactionUseCase(repository),
            deleteTransactionUseCase = DeleteTransactionUseCase(repository),
            getAllTransactionsUseCase = GetAllTransactionsUseCase(repository),
            getTransactionByIdUseCase = GetTransactionByIdUseCase(repository),
            updateTransactionUseCase = UpdateTransactionUseCase(repository)
        )
    }


}