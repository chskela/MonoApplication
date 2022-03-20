package com.chskela.monoapplication.presentation.screens.monthreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.monthreport.usecase.MonthReportUseCases
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MonthReportViewModels @Inject constructor(
    private val monthReportUseCases: MonthReportUseCases
) : ViewModel() {

    private var calendar = Calendar.getInstance()
    private var allTransactionUi: List<TransactionUi> = emptyList()
    private var expenseTransactionUi: List<TransactionUi> = emptyList()
    private var incomeTransactionUi: List<TransactionUi> = emptyList()

    var uiState: MutableState<MonthReportUiState> = mutableStateOf(
        MonthReportUiState(
            currentData = formatDate(calendar.time)
        )
    )
        private set

    init {
        getAllTransactionByMonth()
    }

    fun onEvent(event: MonthReportEvent) {
        when (event) {
            is MonthReportEvent.SelectTab -> {
                val transactionList = when (event.tab) {
                    1 -> expenseTransactionUi
                    2 -> incomeTransactionUi
                    else -> allTransactionUi
                }
                uiState.value = uiState.value.copy(
                    currentTab = event.tab,
                    transactionList = transactionList
                )
            }
            is MonthReportEvent.PreviousMonth -> {
                calendar.add(Calendar.MONTH, -1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
                getAllTransactionByMonth()
            }
            is MonthReportEvent.NextMonth -> {
                calendar.add(Calendar.MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
                getAllTransactionByMonth()
            }
        }
    }

    private fun getAllTransactionByMonth() {
        monthReportUseCases.getAllTransactionsByMonthUseCase(calendar.get(Calendar.MONTH)).onEach { list ->
            allTransactionUi = list.map {
                TransactionUi(
                    id = it.id,
                    timestamp = it.timestamp,
                    amount = it.amount,
                    note = it.note,
                    type = if (it.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income,
                    category = it.name,
                    icon = it.icon,
                )
            }.also { uiState.value = uiState.value.copy(transactionList = it) }

            expenseTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Expense }

            incomeTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Income }

        }.launchIn(viewModelScope)
    }


    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}