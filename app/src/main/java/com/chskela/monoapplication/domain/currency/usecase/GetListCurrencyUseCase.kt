package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetListCurrencyUseCase (private val currencyRepository: CurrencyRepository) {

    operator fun invoke() : Flow<List<Currency>> {
        return currencyRepository.getListCurrency()
    }
}