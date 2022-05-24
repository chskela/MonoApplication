package com.chskela.monoapplication.presentation.screens.reports

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.usecase.ReportsUseCases
import com.chskela.monoapplication.presentation.screens.reports.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.reports.models.TypeTransaction
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.screens.reports.models.ReportsUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReportsViewModels @Inject constructor(
    private val reportsUseCases: ReportsUseCases,
    private val currencyUseCases: CurrencyUseCases,
    private val categoryUseCases: CategoryUseCases,
) : ViewModel() {

    private var calendar = Calendar.getInstance()
    private var allTransactionUi: List<TransactionUi> = emptyList()
    private var expenseTransactionUi: List<TransactionUi> = emptyList()
    private var incomeTransactionUi: List<TransactionUi> = emptyList()

    var uiState: MutableState<ReportsUiState> = mutableStateOf(
        ReportsUiState(
            title = R.string.month_report,
            currentData = formatDate(calendar.time)
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
                calendar.add(Calendar.MONTH, -1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
                initialUiState()
            }
            is ReportsEvent.NextMonth -> {
                calendar.add(Calendar.MONTH, 1)
                uiState.value = uiState.value.copy(currentData = formatDate(calendar.time))
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

    private fun getCategoryList(): Flow<List<Category>> {
        return categoryUseCases.getAllCategoryUseCase()
    }

    private fun getMonthReport(): Flow<MonthReport> {
        return combine(
            reportsUseCases.getAllTransactionsByMonthUseCase(calendar.get(Calendar.MONTH)),
            reportsUseCases.getCurrentBalanceUseCase(),
            reportsUseCases.getExpenseUseCase(),
            reportsUseCases.getIncomeUseCase(),
            currencyUseCases.getDefaultCurrencyUseCase(),
        ) { list, currentBalance, expense, income, id ->
            MonthReport(
                list = list,
                currentBalance = currentBalance,
                expense = expense,
                income = income,
                id = id
            )
        }
    }

    internal data class MonthReport(
        val list: List<TransactionWithCategory>,
        val currentBalance: Double,
        val expense: Double,
        val income: Double,
        val id: Long,
    )

    private fun initialUiState() {
        combine(getMonthReport(), getCategoryList()) { monthReport, categoryReport ->
            allTransactionUi = monthReport.list.map {
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
                currentBalance = monthReport.currentBalance / 100,
                expense = monthReport.expense / 100,
                income = monthReport.income / 100,
                expenseIncome = (monthReport.income - monthReport.expense) / 100,
                currency = currencyUseCases.getCurrencyByIdUseCase(monthReport.id).symbol,
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