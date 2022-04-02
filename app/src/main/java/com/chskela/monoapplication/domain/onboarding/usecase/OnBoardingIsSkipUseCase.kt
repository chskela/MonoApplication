package com.chskela.monoapplication.domain.onboarding.usecase

import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class OnBoardingIsSkipUseCase(private val onBoardingRepository: OnBoardingRepository) {
    operator fun invoke(): Flow<Boolean> {
        return onBoardingRepository.onBoardingIsSkip()
    }
}