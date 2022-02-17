package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyRepositoryImpl : CurrencyRepository {
    override fun getListCurrency(): Flow<List<Currency>> {
        TODO("Not yet implemented")
    }

    override fun getDefaultCurrency(): Flow<Currency> {
        TODO("Not yet implemented")
    }

    override suspend fun addCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }

    override suspend fun setDefaultCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }
}