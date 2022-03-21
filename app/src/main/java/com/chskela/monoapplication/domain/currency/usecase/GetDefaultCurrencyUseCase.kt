package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetDefaultCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    operator fun invoke(): Flow<Long> {
        return currencyRepository.getDefaultCurrency()
    }
}