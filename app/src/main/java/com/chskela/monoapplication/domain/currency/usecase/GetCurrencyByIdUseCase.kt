package com.chskela.monoapplication.domain.currency.usecase

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository

class GetCurrencyByIdUseCase (private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(id: Long) : Currency {
        return currencyRepository.getCurrencyById(id)
    }
}