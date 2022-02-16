package com.chskela.monoapplication.presentation.screens.onboarding.models

sealed class Pages(val pageNumber: Int = 1) {
    object First : Pages()
    object Second : Pages(pageNumber = 2)
    object Third : Pages(pageNumber = 3)

}
