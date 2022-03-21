package com.chskela.monoapplication.presentation.screens.monthreport

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.domain.monthreport.usecase.MonthReportUseCases
import com.chskela.monoapplication.presentation.screens.monthreport.models.MonthReportUiState
import com.chskela.monoapplication.presentation.screens.monthreport.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.monthreport.models.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MonthReportViewModels @Inject constructor(
    private val monthReportUseCases: MonthReportUseCases,
    private val currencyUseCases: CurrencyUseCases
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
        initialUiState()
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
                initialUiState()
            }
            is MonthReportEvent.NextMonth -> {
                calendar.add(Calendar.MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
                initialUiState()
            }
        }
    }

    private fun initialUiState() {
        combine(
            monthReportUseCases.getAllTransactionsByMonthUseCase(calendar.get(Calendar.MONTH)),
            monthReportUseCases.getCurrentBalanceUseCase(),
            monthReportUseCases.getExpenseUseCase(),
            monthReportUseCases.getIncomeUseCase(),
            currencyUseCases.getDefaultCurrencyUseCase()
        ) { list, currentBalance, expense, income, id ->
            allTransactionUi = list.map {
                TransactionUi(
                    id = it.id,
                    timestamp = it.timestamp,
                    amount = it.amount / 100,
                    note = it.note,
                    type = if (it.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income,
                    category = it.name,
                    icon = it.icon,
                )
            }.also {
                uiState.value = uiState.value.copy(
                    transactionList = it,
                    currentBalance = currentBalance / 100,
                    expense = expense / 100,
                    income = income / 100,
                    expenseIncome = (income - expense) / 100,
                    currency = currencyUseCases.getCurrencyByIdUseCase(id).symbol
                )
            }

            expenseTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Expense }

            incomeTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Income }

        }.launchIn(viewModelScope)
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}