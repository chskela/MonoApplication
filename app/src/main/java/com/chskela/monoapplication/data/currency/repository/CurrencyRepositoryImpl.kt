package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.store.CurrencyStore
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import com.chskela.monoapplication.mappers.mapToDomain
import com.chskela.monoapplication.mappers.mapToData
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
            .map { list -> list.map { it.mapToDomain() } }
            .flowOn(coroutineContext)
    }

    override suspend fun getCurrencyById(id: Long): Currency = withContext(coroutineContext) {
        currencyDao.getCurrencyById(id).mapToDomain()
    }

    override fun getIdDefaultCurrency(): Flow<Long> {
        return currencyStore.getDefaultCurrency
            .distinctUntilChanged()
            .flowOn(coroutineContext)
    }

    override suspend fun insertCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.insertCurrency(currency.mapToData())
        }
    }

    override suspend fun updateCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.updateCurrency(currency.mapToData())
        }
    }

    override suspend fun deleteCurrency(currency: Currency) {
        withContext(coroutineContext) {
            currencyDao.deleteCurrency(currency.mapToData())
        }
    }

    override suspend fun setDefaultCurrency(id: Long) {
        withContext(coroutineContext) {
            currencyStore.saveDefaultCurrency(id)
        }
    }
}