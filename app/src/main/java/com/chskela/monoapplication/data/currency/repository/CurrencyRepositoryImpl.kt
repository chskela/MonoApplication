package com.chskela.monoapplication.data.currency.repository

import com.chskela.monoapplication.data.currency.storage.database.CurrencyDao
import com.chskela.monoapplication.data.currency.storage.models.CurrencyData
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(private val currencyDao: CurrencyDao) : CurrencyRepository {

    override fun getListCurrency(): Flow<List<Currency>> {
        return currencyDao.getListCurrency().map { list ->
            list.map {
                Currency(
                    id = it.id ?: 0,
                    name = it.name,
                    letterCode = it.letterCode,
                    symbol = it.symbol
                )
            }
        }
    }

    override fun getDefaultCurrency(): Flow<Currency> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCurrency(currency: Currency) {
//        val item = CurrencyData(
//            name = currency.name,
//            letterCode = currency.letterCode,
//            symbol = currency.symbol
//        )
//        currencyDao.insertCurrency(item)
    }

    override suspend fun updateCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }

    override suspend fun setDefaultCurrency(currency: Currency) {
        TODO("Not yet implemented")
    }
}