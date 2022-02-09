package com.chskela.monoapplication.data.onboarding

import javax.inject.Inject

class OnBoardingRepository @Inject constructor() {

    private val list = OnBoardingData.getData()

    val firstPage = list[0]
    val secondPage = list[1]
    val thirdPage = list[2]
}