package com.chskela.monoapplication.domain.onboarding.usecase

import com.chskela.monoapplication.data.onboarding.repository.FakeOnBoardingRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class OnBoardingIsSkipUseCaseTest {

    private lateinit var onBoardingIsSkipUseCase: OnBoardingIsSkipUseCase
    private lateinit var fakeOnBoardingRepository: FakeOnBoardingRepository

    @Before
    fun setUp() {
        fakeOnBoardingRepository = FakeOnBoardingRepository()
        onBoardingIsSkipUseCase = OnBoardingIsSkipUseCase(fakeOnBoardingRepository)
    }

    @Test
    fun `The value is false`() {
        val expected = runBlocking {
            onBoardingIsSkipUseCase().last()
        }

        assertEquals(expected, false)
    }
}