package com.chskela.monoapplication.presentation.screens.transaction

sealed class TransitionEvent{
    data class SelectTab(val tab: Int) : TransitionEvent()
    data class ChangeAmount(val value: String) : TransitionEvent()
    data class ChangeNote(val value: String) : TransitionEvent()
    data class SelectCategory(val categoryId: Long) : TransitionEvent()
    object Submit : TransitionEvent()
    object PreviousDate: TransitionEvent()
    object NextDate: TransitionEvent()
}
