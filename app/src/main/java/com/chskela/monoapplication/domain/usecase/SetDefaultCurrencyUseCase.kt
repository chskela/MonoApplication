package com.chskela.monoapplication.domain.usecase

import com.chskela.monoapplication.domain.models.Currency
import com.chskela.monoapplication.domain.repository.CurrencyRepository

class SetDefaultCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(currency: Currency) {
        currencyRepository.setDefaultCurrency(currency = currency)
    }
}