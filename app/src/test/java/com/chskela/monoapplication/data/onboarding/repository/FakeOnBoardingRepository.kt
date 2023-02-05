package com.chskela.monoapplication.data.onboarding.repository

import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeOnBoardingRepository : OnBoardingRepository {

    var value = false

    override fun onBoardingIsSkip(): Flow<Boolean> {
        return flow { emit(value) }
    }

    override suspend fun setOnBoardingIsSkip() {
        value = true
    }
}