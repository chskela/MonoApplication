package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetDefaultCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Flow<Currency> {
        return combine(currencyRepository.getIdDefaultCurrency()) {
            currencyRepository.getCurrencyById(it.last())
        }
    }
}