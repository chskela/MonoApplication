package com.chskela.monoapplication.data.onboarding.repository

import com.chskela.monoapplication.data.onboarding.storage.store.OnBoardingStore
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.onboarding.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class OnBoardingRepositoryImpl(private val onBoardingStore: OnBoardingStore) : OnBoardingRepository,
    AbstractRepository() {
    override fun onBoardingIsSkip(): Flow<Boolean> {
        return onBoardingStore.onBoardingIsSkip.flowOn(coroutineContext)
    }

    override suspend fun setOnBoardingIsSkip() {
        withContext(coroutineContext) {
            onBoardingStore.setOnBoardingIsSkip()
        }
    }
}