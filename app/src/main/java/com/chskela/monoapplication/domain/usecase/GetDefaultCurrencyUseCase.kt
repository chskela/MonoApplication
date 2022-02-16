package com.chskela.monoapplication.domain.usecase

import com.chskela.monoapplication.domain.models.Currency
import com.chskela.monoapplication.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetDefaultCurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    operator fun invoke() : Flow<Currency> {
        return currencyRepository.getDefaultCurrency()
    }
}