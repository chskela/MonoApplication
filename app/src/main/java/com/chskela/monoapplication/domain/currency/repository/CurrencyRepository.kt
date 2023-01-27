package com.chskela.monoapplication.domain.currency.repository

import com.chskela.monoapplication.domain.currency.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getListCurrency() : Flow<List<Currency>>

    suspend fun getCurrencyById(id: Long) : Currency

    fun getIdDefaultCurrency() : Flow<Long>

    suspend fun insertCurrency(currency: Currency)

    suspend fun updateCurrency(currency: Currency)

    suspend fun  deleteCurrency(currency: Currency)

    suspend fun setDefaultCurrency(id: Long)
}