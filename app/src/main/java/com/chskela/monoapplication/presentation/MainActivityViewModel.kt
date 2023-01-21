package com.chskela.monoapplication.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.onboarding.usecase.OnBoardingIsSkipUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    onBoardingIsSkipUseCase: OnBoardingIsSkipUseCase
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    var onBoardingIsSkip: MutableState<Boolean> = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingIsSkipUseCase().collect {
              onBoardingIsSkip.value = it
            }
            _isLoading.value = false
        }
    }
}