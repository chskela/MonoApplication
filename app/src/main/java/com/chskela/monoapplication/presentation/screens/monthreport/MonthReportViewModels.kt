package com.chskela.monoapplication.presentation.screens.monthreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonthReportViewModels @Inject constructor() : ViewModel() {
    var uiState: MutableState<MonthReportUiState> = mutableStateOf(MonthReportUiState())
        private set

}