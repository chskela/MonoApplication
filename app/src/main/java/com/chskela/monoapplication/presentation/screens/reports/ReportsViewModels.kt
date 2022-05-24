package com.chskela.monoapplication.presentation.screens.reports

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsUseCase
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.screens.reports.models.ReportsUiState
import com.chskela.monoapplication.presentation.screens.reports.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.reports.models.TypeTransaction
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReportsViewModels @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val currencyUseCases: CurrencyUseCases,
    private val categoryUseCases: CategoryUseCases,
) : ViewModel() {

    private var currentCalendar = Calendar.getInstance()
    private var allTransactionUi: List<TransactionUi> = emptyList()
    private var expenseTransactionUi: List<TransactionUi> = emptyList()
    private var incomeTransactionUi: List<TransactionUi> = emptyList()

    var uiState: MutableState<ReportsUiState> = mutableStateOf(
        ReportsUiState(
            title = R.string.month_report,
            currentData = formatDate(currentCalendar.time)
        )
    )
        private set

    init {
        initialUiState()
    }

    fun onEvent(event: ReportsEvent) {
        when (event) {
            is ReportsEvent.SelectTab -> {
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
            is ReportsEvent.PreviousMonth -> {
                currentCalendar.add(Calendar.MONTH, -1)
                uiState.value = uiState.value.copy(currentData = formatDate(currentCalendar.time))
                initialUiState()
            }
            is ReportsEvent.NextMonth -> {
                currentCalendar.add(Calendar.MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(currentCalendar.time))
                initialUiState()
            }
            is ReportsEvent.SelectReport -> {
                val newReport = when (event.tab) {
                    Report.Category -> Report.Month
                    Report.Month -> Report.Category
                }
                val title = when (event.tab) {
                    Report.Category -> R.string.month_report
                    Report.Month -> R.string.category_report
                }
                uiState.value = uiState.value.copy(report = newReport, title = title)
            }
            ReportsEvent.ToggleVisible -> uiState.value =
                uiState.value.copy(isVisibleModal = !uiState.value.isVisibleModal)
        }
    }

    private fun initialUiState() {
        combine(
            getAllTransactionsUseCase(),
            currencyUseCases.getDefaultCurrencyUseCase(),
            categoryUseCases.getAllCategoryUseCase()
        ) { allTransactions, currencyId, categoryReport ->
            val calendar = Calendar.getInstance()

            val currentBalance =
                allTransactions.sumOf { if (it.type == TypeCategory.Expense) -it.amount else it.amount }
                    .toDouble() / 100

            val listByMonth = allTransactions.filter {
                calendar.timeInMillis = it.timestamp
                calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
            }

            val (incomeList, expenseList) = listByMonth.partition { it.type == TypeCategory.Income }

            val incomeByMonth = incomeList.sumOf { it.amount }.toDouble() / 100

            val expenseByMonth = expenseList.sumOf { it.amount }.toDouble() / 100

            allTransactionUi = listByMonth.map {
                TransactionUi(
                    id = it.id,
                    timestamp = it.timestamp,
                    amount = it.amount / 100.0,
                    note = it.note,
                    type = if (it.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income,
                    category = it.name,
                    icon = it.icon,
                )
            }

            expenseTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Expense }

            incomeTransactionUi = allTransactionUi.filter { it.type == TypeTransaction.Income }

            uiState.value = uiState.value.copy(
                transactionList = allTransactionUi,
                currentBalance = currentBalance,
                expense = expenseByMonth,
                income = incomeByMonth,
                expenseIncome = incomeByMonth - expenseByMonth,
//                previousBalance = currentBalance - (incomeByMonth - expenseByMonth),
                currency = currencyUseCases.getCurrencyByIdUseCase(currencyId).symbol,
                expenseList = categoryReport
                    .filter { category -> category.type == TypeCategory.Expense }
                    .map { item -> CategoryUi(id = item.id, icon = item.icon, title = item.name) },
                incomeList = categoryReport
                    .filter { category -> category.type == TypeCategory.Income }
                    .map { item -> CategoryUi(id = item.id, icon = item.icon, title = item.name) },
            )
        }.launchIn(viewModelScope)
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)
}