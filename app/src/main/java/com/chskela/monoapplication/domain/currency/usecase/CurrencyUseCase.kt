package com.chskela.monoapplication.domain.currency.usecase

data class CurrencyUseCase(
    val addCurrencyUseCase: AddCurrencyUseCase,
    val deleteCurrencyUseCase: DeleteCurrencyUseCase,
    val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    val getListCurrencyUseCase: GetListCurrencyUseCase,
    val setDefaultCurrencyUseCase: SetDefaultCurrencyUseCase
)
