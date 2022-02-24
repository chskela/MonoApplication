package com.chskela.monoapplication.presentation.screens.transaction

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransitionViewModel @Inject constructor() : ViewModel() {

    private var currentDate = Calendar.getInstance()

    var uiState: MutableState<TransitionUiState> =
        mutableStateOf(TransitionUiState(currentData = formatDate(currentDate.time)))
        private set

    fun onEvent(event: TransitionEvent) {
        when (event) {
            is TransitionEvent.SelectTab -> {
                uiState.value = uiState.value.copy(currentTab = event.tab)
            }
            is TransitionEvent.ChangeAmount -> {
                uiState.value = uiState.value.copy(amount = event.value)
                uiState.value = uiState.value.copy(enabledButton = isEnabled())
            }
            is TransitionEvent.ChangeNote -> {
                uiState.value = uiState.value.copy(note = event.value)
                uiState.value = uiState.value.copy(enabledButton = isEnabled())
            }
            is TransitionEvent.SelectCategory -> {
                uiState.value = uiState.value.copy(currentCategory = event.categoryId)
            }
            is TransitionEvent.Submit -> {TODO()}
            is TransitionEvent.PreviousData -> {
                currentDate.add(Calendar.DAY_OF_MONTH, -1)
                uiState.value = uiState.value.copy(currentData = formatDate(currentDate.time))
            }
            is TransitionEvent.NextData -> {
                currentDate.add(Calendar.DAY_OF_MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(currentDate.time))
            }
        }
    }

    private fun isEnabled() = uiState.value.amount.isNotEmpty() && uiState.value.note.isNotEmpty()

    private fun formatDate(date: Date) =
        SimpleDateFormat("dd MMMM yyyy (EEEE)", Locale.getDefault()).format(date)
}