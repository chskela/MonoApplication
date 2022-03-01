package com.chskela.monoapplication.domain.currency.usecase

data class CurrencyUseCases(
    val addCurrencyUseCase: AddCurrencyUseCase,
    val updateCurrencyUseCase: UpdateCurrencyUseCase,
    val deleteCurrencyUseCase: DeleteCurrencyUseCase,
    val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    val getListCurrencyUseCase: GetListCurrencyUseCase,
    val setDefaultCurrencyUseCase: SetDefaultCurrencyUseCase,
    val getCurrencyByIdUseCase: GetCurrencyByIdUseCase
)
