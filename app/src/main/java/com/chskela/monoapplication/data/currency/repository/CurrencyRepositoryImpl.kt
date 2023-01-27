package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.store.CurrencyStore
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.mappers.mapToCurrency
import com.chskela.monoapplication.mappers.mapToCurrencyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currencyStore: CurrencyStore
) : CurrencyRepository {

    override fun getListCurrency(): Flow<List<Currency>> {
        return currencyDao.getListCurrency()
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToCurrency() }
            }
    }

    override suspend fun getCurrencyById(id: Long): Currency {
        return currencyDao.getCurrencyById(id).mapToCurrency()
    }

    override fun getIdDefaultCurrency(): Flow<Long> {
        return currencyStore.getDefaultCurrency.distinctUntilChanged()
    }

    override suspend fun insertCurrency(currency: Currency) {
        currencyDao.insertCurrency(currency.mapToCurrencyEntity())
    }

    override suspend fun updateCurrency(currency: Currency) {
        currencyDao.updateCurrency(currency.mapToCurrencyEntity())
    }

    override suspend fun deleteCurrency(currency: Currency) {
        currencyDao.deleteCurrency(currency.mapToCurrencyEntity())
    }

    override suspend fun setDefaultCurrency(id: Long) {
        currencyStore.saveDefaultCurrency(id)
    }
}