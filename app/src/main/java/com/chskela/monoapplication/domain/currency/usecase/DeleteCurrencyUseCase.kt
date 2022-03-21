package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository

class DeleteCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(currency: Currency) {
        currencyRepository.deleteCurrency(currency)
    }
}