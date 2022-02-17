package com.chskela.monoapplication.presentation.screens.currency

import com.chskela.monoapplication.domain.currency.models.Currency

data class CurrencyUiState(
    val currencyList: List<Currency> = emptyList(),
    val selectedCurrency: Long = 1,
)
