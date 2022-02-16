package com.chskela.monoapplication.presentation.screens.onboarding

import com.chskela.monoapplication.presentation.screens.onboarding.models.Pages

sealed class OnBoardingEvent{
    data class NextPage(val page: Pages = Pages.First) : OnBoardingEvent()
    object Skip : OnBoardingEvent()
}
