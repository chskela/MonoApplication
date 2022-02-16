package com.chskela.monoapplication.presentation.screens.currency

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor() : ViewModel() {
    var uiState: MutableState<CurrencyUiState> = mutableStateOf(CurrencyUiState())
        private set

}
