package com.chskela.monoapplication.presentation.screens.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.onboarding.usecase.SetOnBoardingIsSkipUseCase
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingPage
import com.chskela.monoapplication.presentation.screens.onboarding.models.OnBoardingUiState
import com.chskela.monoapplication.presentation.screens.onboarding.models.Pages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setOnBoardingIsSkipUseCase: SetOnBoardingIsSkipUseCase
) : ViewModel() {

    private val pageFirst = OnBoardingPage(
        page = Pages.First,
        image = R.drawable.on_boarding_step1,
        title = R.string.on_boarding_title_step1,
        body = R.string.on_boarding_body_step1,
        buttonTitle = R.string.on_boarding_continue,
    )
    private val pageSecond = OnBoardingPage(
        page = Pages.Second,
        image = R.drawable.on_boarding_step2,
        title = R.string.on_boarding_title_step2,
        body = R.string.on_boarding_body_step2,
        buttonTitle = R.string.on_boarding_continue,
    )
    private val pageThird = OnBoardingPage(
        page = Pages.Third,
        image = R.drawable.on_boarding_step3,
        title = R.string.on_boarding_title_step3,
        body = R.string.on_boarding_body_step3,
        buttonTitle = R.string.on_boarding_get_started,
    )

    var uiState: OnBoardingUiState by mutableStateOf(
        OnBoardingUiState(
            onBoardingPage = pageFirst,
            skip = true,
        )
    )


    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.NextPage -> {
                when (event.page) {
                    is Pages.First -> {
                        uiState = uiState.copy(onBoardingPage = pageSecond)
                    }
                    is Pages.Second -> {
                        uiState = uiState.copy(onBoardingPage = pageThird, skip = false)
                    }
                    is Pages.Third -> setOnBoardingIsSkip()
                }
            }
            is OnBoardingEvent.Skip -> setOnBoardingIsSkip()
        }
    }

    private fun setOnBoardingIsSkip() {
        viewModelScope.launch {
            setOnBoardingIsSkipUseCase()
        }
    }
}