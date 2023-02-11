package com.chskela.monoapplication.di

import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.category.usecase.*
import com.chskela.monoapplication.domain.common.usecase.CurrencyFormatUseCase
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.domain.currency.usecase.*
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import com.chskela.monoapplication.domain.reports.usecase.*
import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import com.chskela.monoapplication.domain.onboarding.usecase.SetOnBoardingIsSkipUseCase
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

    @Provides
    fun provideCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            getAllCategoryUseCase = GetAllCategoryUseCase(repository),
            addCategoryUseCase = AddCategoryUseCase(repository),
            deleteCategoryUseCase = DeleteCategoryUseCase(repository),
            updateCategoryUseCase = UpdateCategoryUseCase(repository),
            getCategoryByIdUseCase = GetCategoryByIdUseCase(repository)
        )
    }

    @Provides
    fun provideGetAllCategoryByTypeUseCase(repository: CategoryRepository): GetAllCategoryByTypeUseCase {
        return  GetAllCategoryByTypeUseCase(repository)
    }

    @Provides
    fun provideTransactionUseCases(repository: TransactionRepository): TransactionUseCases {
        return TransactionUseCases(
            addTransactionUseCase = AddTransactionUseCase(repository),
            deleteTransactionUseCase = DeleteTransactionUseCase(repository),
            getTransactionByIdUseCase = GetTransactionByIdUseCase(repository),
            updateTransactionUseCase = UpdateTransactionUseCase(repository)
        )
    }

    @Provides
    fun provideAddTransactionUseCase(repository: TransactionRepository): AddTransactionUseCase {
        return AddTransactionUseCase(repository)
    }

    @Provides
    fun provideDeleteAllTransactionsUseCase(repository: TransactionRepository): DeleteAllTransactionsUseCase {
        return DeleteAllTransactionsUseCase(repository)
    }

    @Provides
    fun provideGetAllTransactionsUseCase(repository: ReportsRepository): GetAllTransactionsUseCase {
        return GetAllTransactionsUseCase(repository)
    }

    @Provides
    fun provideGetAllTransactionsByCategoryUseCase(repository: ReportsRepository): GetAllTransactionsByMonthAndCategoryUseCase {
        return GetAllTransactionsByMonthAndCategoryUseCase(repository)
    }

    @Provides
    fun provideGetAmountByCategoryPerMonthUseCase(repository: ReportsRepository): GetAmountByCategoryPerMonthUseCase {
        return GetAmountByCategoryPerMonthUseCase(repository)
    }

    @Provides
    fun provideSetOnBoardingIsSkipUseCase(repository: OnBoardingRepository): SetOnBoardingIsSkipUseCase {
        return SetOnBoardingIsSkipUseCase(repository)
    }

    @Provides
    fun provideCurrencyFormatUseCase(): CurrencyFormatUseCase {
        return CurrencyFormatUseCase()
    }
}