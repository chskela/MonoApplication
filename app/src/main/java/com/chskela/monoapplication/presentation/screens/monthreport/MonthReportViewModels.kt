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


    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}