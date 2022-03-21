package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository

class AddCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(currency: Currency) {
        currencyRepository.insertCurrency(currency)
    }
}