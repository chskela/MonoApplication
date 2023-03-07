package com.chskela.monoapplication.presentation.screens.currency

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.usecase.AddCurrencyUseCase
import com.chskela.monoapplication.domain.currency.usecase.GetDefaultCurrencyUseCase
import com.chskela.monoapplication.domain.currency.usecase.GetListCurrencyUseCase
import com.chskela.monoapplication.domain.currency.usecase.SetDefaultCurrencyUseCase
import com.chskela.monoapplication.presentation.screens.currency.models.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val addCurrencyUseCase: AddCurrencyUseCase,
    private val setDefaultCurrencyUseCase: SetDefaultCurrencyUseCase,
    private val getListCurrencyUseCase: GetListCurrencyUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,

    ) :
    ViewModel() {
    var uiState: CurrencyUiState by mutableStateOf(CurrencyUiState())


    init {
        getCurrency()
    }

    fun add(currency: Currency) {
        viewModelScope.launch {
            addCurrencyUseCase(currency)
        }
    }

    fun selectDefaultCurrency(id: Long) {
        uiState = uiState.copy(selectedCurrency = id)
        viewModelScope.launch {
            setDefaultCurrencyUseCase(id)
        }
    }

    private fun getCurrency() {
        combine(
            getListCurrencyUseCase(),
            getDefaultCurrencyUseCase()
        ) { list, currency ->
            uiState = uiState.copy(currencyList = list, selectedCurrency = currency.id)
        }.launchIn(viewModelScope)
    }
}
