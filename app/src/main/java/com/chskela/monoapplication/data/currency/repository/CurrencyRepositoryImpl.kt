package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.store.CurrencyStore
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.mappers.mapToCurrency
import com.chskela.monoapplication.mappers.mapToCurrencyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currencyStore: CurrencyStore
) : CurrencyRepository, AbstractRepository() {

    override fun getListCurrency(): Flow<List<Currency>> {
        return currencyDao.getListCurrency()
            .distinctUntilChanged()
            .map { list -> list.map { it.mapToCurrency() } }
            .flowOn(coroutineContext)
    }

    override suspend fun getCurrencyById(id: Long): Currency = withContext(coroutineContext) {
        currencyDao.getCurrencyById(id).mapToCurrency()
    }

    override fun getIdDefaultCurrency(): Flow<Long> {
        return currencyStore.getDefaultCurrency
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }

    override suspend fun insertCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.insertCurrency(currency.mapToCurrencyEntity())
        }
    }

    override suspend fun updateCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.updateCurrency(currency.mapToCurrencyEntity())
        }
    }

    override suspend fun deleteCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.deleteCurrency(currency.mapToCurrencyEntity())
        }
    }

    override suspend fun setDefaultCurrency(id: Long) {
        withContext(coroutineContext) {
            currencyStore.saveDefaultCurrency(id)
        }
    }
}