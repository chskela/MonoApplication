package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository

class SetDefaultCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(id: Long) {
        currencyRepository.setDefaultCurrency(id)
    }
}