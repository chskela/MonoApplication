package com.chskela.monoapplication.presentation.screens.currency

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.presentation.screens.currency.models.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyUseCases: CurrencyUseCases) : ViewModel() {
    var uiState: MutableState<CurrencyUiState> = mutableStateOf(CurrencyUiState())
        private set

    init {
        getCurrency()
    }

    fun add(currency: Currency) {
        viewModelScope.launch {
            currencyUseCases.addCurrencyUseCase(currency)
        }
    }

    fun selectDefaultCurrency(id: Long) {
        uiState.value = uiState.value.copy(selectedCurrency = id)
    }

    private fun getCurrency() {
       currencyUseCases.getListCurrencyUseCase().onEach {
           uiState.value = uiState.value.copy(currencyList = it, selectedCurrency = it.first().id)
       }
           .launchIn(viewModelScope)
    }

}
