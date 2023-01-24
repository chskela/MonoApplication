package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.currency.storage.models.CurrencyEntity
import com.chskela.monoapplication.domain.currency.models.Currency

fun CurrencyEntity.mapToCurrency() = Currency(
    id = id,
    name = name,
    letterCode = letterCode,
    symbol = symbol
)

fun Currency.mapToCurrencyEntity() = CurrencyEntity(
    id = id,
    name = name,
    letterCode = letterCode,
    symbol = symbol
)