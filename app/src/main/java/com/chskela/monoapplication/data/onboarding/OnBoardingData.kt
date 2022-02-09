package com.chskela.monoapplication.data.onboarding

import com.chskela.monoapplication.R
import com.chskela.monoapplication.screens.onboarding.OnBoardingUiState

object OnBoardingData {

    fun getData() = listOf(
            OnBoardingUiState(
                page = 1,
                image = R.drawable.on_boarding_step1,
                title = R.string.on_boarding_title_step1,
                body = R.string.on_boarding_body_step1,
                buttonTitle = R.string.on_boarding_continue,
                skip = true
            ),
            OnBoardingUiState(
                page = 2,
                image = R.drawable.on_boarding_step2,
                title = R.string.on_boarding_title_step2,
                body = R.string.on_boarding_body_step2,
                buttonTitle = R.string.on_boarding_continue,
                skip = true
            ),
            OnBoardingUiState(
                page = 3,
                image = R.drawable.on_boarding_step3,
                title = R.string.on_boarding_title_step3,
                body = R.string.on_boarding_body_step3,
                buttonTitle = R.string.on_boarding_get_started,
                skip = false
            ),
    )


}

