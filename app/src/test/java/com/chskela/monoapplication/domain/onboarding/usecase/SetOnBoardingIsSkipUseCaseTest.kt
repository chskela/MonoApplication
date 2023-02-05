package com.chskela.monoapplication.domain.onboarding.usecase

import com.chskela.monoapplication.data.onboarding.repository.FakeOnBoardingRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SetOnBoardingIsSkipUseCaseTest {

    private lateinit var setOnBoardingIsSkipUseCase: SetOnBoardingIsSkipUseCase
    private lateinit var fakeOnBoardingRepository: FakeOnBoardingRepository

    @Before
    fun setUp() {
        fakeOnBoardingRepository = FakeOnBoardingRepository()
        setOnBoardingIsSkipUseCase = SetOnBoardingIsSkipUseCase(fakeOnBoardingRepository)
    }

    @Test
    fun `Sets the value to true`() {
        val expected = runBlocking {
            setOnBoardingIsSkipUseCase()
            fakeOnBoardingRepository.value
        }

        assertEquals(expected, true)
    }
}