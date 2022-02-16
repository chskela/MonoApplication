package com.chskela.monoapplication.domain.usecase

import com.chskela.monoapplication.domain.models.Currency
import com.chskela.monoapplication.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetListCurrencyUseCase (private val currencyRepository: CurrencyRepository) {

    operator fun invoke() : Flow<List<Currency>> {
        return currencyRepository.getListCurrency()
    }
}