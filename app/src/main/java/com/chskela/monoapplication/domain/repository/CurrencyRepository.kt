package com.chskela.monoapplication.domain.repository

import com.chskela.monoapplication.domain.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getListCurrency() : Flow<List<Currency>>

    fun getDefaultCurrency() : Flow<Currency>

    suspend fun addCurrency(currency: Currency)

    suspend fun  deleteCurrency(currency: Currency)

    suspend fun setDefaultCurrency(currency: Currency)
}