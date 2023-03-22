package com.chskela.monoapplication.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.onboarding.usecase.OnBoardingIsSkipUseCase
import com.chskela.monoapplication.domain.themeswitcher.usecase.IsDarkThemeUseCase
import com.chskela.monoapplication.domain.themeswitcher.usecase.SetDarkThemeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    isDarkThemeUseCase: IsDarkThemeUseCase,
    onBoardingIsSkipUseCase: OnBoardingIsSkipUseCase,
    private val setDarkThemeUseCase: SetDarkThemeUseCase
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    var onBoardingIsSkip: MutableState<Boolean> = mutableStateOf(true)
        private set

    val isDarkTheme: StateFlow<Boolean?> = isDarkThemeUseCase().stateIn(
        viewModelScope,
        started = SharingStarted.Eagerly,
        null
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingIsSkipUseCase().collect {
                onBoardingIsSkip.value = it
            }
            _isLoading.value = false
        }
    }

    fun setTheme(value: Boolean) {
        viewModelScope.launch {
            if (isDarkTheme.value == null) {
                setDarkThemeUseCase(value)
            }
        }
    }
}