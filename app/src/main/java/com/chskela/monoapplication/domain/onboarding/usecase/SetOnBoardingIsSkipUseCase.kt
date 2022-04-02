package com.chskela.monoapplication.domain.onboarding.usecase

import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository

class SetOnBoardingIsSkipUseCase(private val onBoardingRepository: OnBoardingRepository) {
    suspend operator fun invoke() {
        return onBoardingRepository.setOnBoardingIsSkip()
    }
}