package com.chskela.monoapplication.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.transaction.usercase.DeleteAllTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val deleteAllTransactionsUseCase: DeleteAllTransactionsUseCase,
) : ViewModel() {

    fun deleteAllData() {
        viewModelScope.launch((Dispatchers.IO) ) {
            deleteAllTransactionsUseCase()
        }
    }
}