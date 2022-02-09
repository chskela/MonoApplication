package com.chskela.monoapplication.screens.onboarding

data class OnBoardingUiState(
        val page: Int,
        val image: Int,
        val title: Int,
        val body: Int,
        val buttonTitle: Int,
        val skip: Boolean,
)