package com.chskela.monoapplication.presentation.screens.monthreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MonthReportViewModels @Inject constructor(
//    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : ViewModel() {

    private var calendar = Calendar.getInstance()

    var uiState: MutableState<MonthReportUiState> = mutableStateOf(MonthReportUiState(
        currentData = formatDate(calendar.time)
    ))
        private set

    fun onEvent(event: MonthReportEvent) {
        when (event) {
            is MonthReportEvent.SelectTab -> {
                uiState.value = uiState.value.copy(
                    currentTab = event.tab,
                )
            }
            is MonthReportEvent.PreviousMonth -> {
                calendar.add(Calendar.MONTH, -1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
            }
            is MonthReportEvent.NextMonth -> {
                calendar.add(Calendar.MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
            }
        }
    }


    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}