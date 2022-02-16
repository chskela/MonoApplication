package com.chskela.monoapplication.presentation.screens.onboarding.models

import com.chskela.monoapplication.presentation.screens.onboarding.OnBoardingEvent

data class OnBoardingUiState(
        val onBoardingPage: OnBoardingPage,
        val skip: Boolean,
//        val eventType: OnBoardingEvent
)