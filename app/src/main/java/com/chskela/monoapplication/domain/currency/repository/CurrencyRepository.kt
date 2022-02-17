package com.chskela.monoapplication.domain.currency.repository

import com.chskela.monoapplication.domain.currency.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getListCurrency() : Flow<List<Currency>>

    fun getDefaultCurrency() : Flow<Currency>

    suspend fun insertCurrency(currency: Currency)

    suspend fun updateCurrency(currency: Currency)

    suspend fun  deleteCurrency(currency: Currency)

    suspend fun setDefaultCurrency(currency: Currency)
}