package com.chskela.monoapplication.presentation.screens.monthreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.domain.transaction.usercase.GetAllTransactionsUseCase
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MonthReportViewModels @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : ViewModel() {

    private var calendar = Calendar.getInstance()

    var uiState: MutableState<MonthReportUiState> = mutableStateOf(
        MonthReportUiState(
            currentData = formatDate(calendar.time)
        )
    )
        private set

    init {
        getAllTransaction()
    }

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

    private fun getAllTransaction() {
        getAllTransactionsUseCase().onEach { list ->
            uiState.value = uiState.value.copy(transactionList = list.map {
                TransactionUi(
                    id = it.id ?: 0,
                    timestamp = it.timestamp,
                    amount = it.amount,
                    note = it.note,
                    type = if (it.type == Type.Expense) TypeTransaction.Expense else TypeTransaction.Income,
                    category = it.name,
                    icon = it.icon,

                    )
            })
        }.launchIn(viewModelScope)
    }


    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}