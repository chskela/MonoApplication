package com.chskela.monoapplication.screens.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.data.onboarding.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingRepository: OnBoardingRepository) :
    ViewModel() {

    var uiState: MutableState<OnBoardingUiState> = mutableStateOf(onBoardingRepository.firstPage)
        private set

    fun secondPage() {
        uiState.value = onBoardingRepository.secondPage
    }

    fun thirdPage() {
        uiState.value = onBoardingRepository.thirdPage
    }
}