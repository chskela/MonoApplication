package com.chskela.monoapplication.data.onboarding.repository

import com.chskela.monoapplication.data.onboarding.storage.store.OnBoardingStore
import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class OnBoardingRepositoryImpl(private val onBoardingStore: OnBoardingStore) : OnBoardingRepository {
    override fun onBoardingIsSkip(): Flow<Boolean> {
        return onBoardingStore.onBoardingIsSkip
    }

    override suspend fun setOnBoardingIsSkip() {
        onBoardingStore.setOnBoardingIsSkip()
    }
}