package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.data.currency.storage.dao.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.models.CurrencyEntity
import com.chskela.monoapplication.data.currency.storage.store.CurrencyStore
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currencyStore: CurrencyStore
) : CurrencyRepository {

    override fun getListCurrency(): Flow<List<Currency>> {
        return currencyDao.getListCurrency().map { list ->
            list.map { mapToDomain(it) }
        }
    }

    override suspend fun getCurrencyById(id: Long): Currency {
        return mapToDomain(currencyDao.getCurrencyById(id))
    }

    override fun getDefaultCurrency(): Flow<Long> {
        return currencyStore.getDefaultCurrency
    }

    override suspend fun insertCurrency(currency: Currency) {
        currencyDao.insertCurrency(mapToStorage(currency))
    }

    override suspend fun updateCurrency(currency: Currency) {
        currencyDao.updateCurrency(mapToStorage(currency))
    }

    override suspend fun deleteCurrency(currency: Currency) {
        currencyDao.deleteCurrency(mapToStorage(currency))
    }

    override suspend fun setDefaultCurrency(id: Long) {
        currencyStore.saveDefaultCurrency(id)
    }

    private fun mapToDomain(currencyEntity: CurrencyEntity) = Currency(
        id = currencyEntity.id ?: 0,
        name = currencyEntity.name,
        letterCode = currencyEntity.letterCode,
        symbol = currencyEntity.symbol
    )

    private fun mapToStorage(currency: Currency) = CurrencyEntity(
        id = currency.id,
        name = currency.name,
        letterCode = currency.letterCode,
        symbol = currency.symbol
    )
}