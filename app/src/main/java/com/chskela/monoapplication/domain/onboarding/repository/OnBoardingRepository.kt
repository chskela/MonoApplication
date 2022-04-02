package com.chskela.monoapplication.domain.onboarding.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    fun onBoardingIsSkip(): Flow<Boolean>

    suspend fun setOnBoardingIsSkip()
}