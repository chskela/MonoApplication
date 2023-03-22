package com.chskela.monoapplication.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.themeswitcher.usecase.IsDarkThemeUseCase
import com.chskela.monoapplication.domain.themeswitcher.usecase.SetDarkThemeUseCase
import com.chskela.monoapplication.domain.transaction.usercase.DeleteAllTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    isDarkThemeUseCase: IsDarkThemeUseCase,
    private val setDarkThemeUseCase: SetDarkThemeUseCase,
    private val deleteAllTransactionsUseCase: DeleteAllTransactionsUseCase
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = isDarkThemeUseCase().stateIn(
        viewModelScope,
        started = SharingStarted.Eagerly,
      true
    )

    fun deleteAllData() {
        viewModelScope.launch {
            deleteAllTransactionsUseCase()
        }
    }

    fun setTheme(value: Boolean){
        viewModelScope.launch {
            setDarkThemeUseCase(value)
        }
    }
}