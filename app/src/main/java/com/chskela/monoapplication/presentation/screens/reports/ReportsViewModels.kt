package com.chskela.monoapplication.presentation.screens.reports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.common.usecase.CurrencyFormatUseCase
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.domain.reports.models.TransactionWithCategory
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsUseCase
import com.chskela.monoapplication.mappers.mapToCategoryUi
import com.chskela.monoapplication.presentation.screens.reports.models.Report
import com.chskela.monoapplication.presentation.screens.reports.models.ReportsUiState
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
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
    private val currencyFormatUseCase: CurrencyFormatUseCase
) : ViewModel() {

    private var currentCalendar = Calendar.getInstance()
    private var allTransactionListUi: List<TransactionListUi> = emptyList()
    private var expenseTransactionListUi: List<TransactionListUi> = emptyList()
    private var incomeTransactionListUi: List<TransactionListUi> = emptyList()

    var uiState: ReportsUiState by mutableStateOf(
        ReportsUiState(
            title = R.string.month_report,
            currentData = formatDate(currentCalendar.time)
        )
    )

    init {
        initialUiState()
    }

    fun onEvent(event: ReportsEvent) {
        when (event) {
            is ReportsEvent.SelectTab -> onSelectTab(event)
            is ReportsEvent.PreviousMonth -> {
                currentCalendar.add(Calendar.MONTH, -1)
                uiState = uiState.copy(currentData = formatDate(currentCalendar.time))
                initialUiState()
            }
            is ReportsEvent.NextMonth -> {
                currentCalendar.add(Calendar.MONTH, 1)
                uiState = uiState.copy(currentData = formatDate(currentCalendar.time))
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
                uiState = uiState.copy(report = newReport, title = title)
            }
            ReportsEvent.ToggleVisible -> uiState =
                uiState.copy(isVisibleModal = !uiState.isVisibleModal)
        }
    }

    private fun onSelectTab(event: ReportsEvent.SelectTab) {
        val transactionList = when (event.tab) {
            1 -> expenseTransactionListUi
            2 -> incomeTransactionListUi
            else -> allTransactionListUi
        }
        uiState = uiState.copy(
            currentTab = event.tab,
            transactionList = transactionList
        )
    }

    private fun initialUiState() {
        combine(
            getAllTransactionsUseCase(),
            currencyUseCases.getDefaultCurrencyUseCase(),
            categoryUseCases.getAllCategoryUseCase()
        ) { allTransactions, currentCurrency, allCategories ->
            val currencyFormat = currencyFormatUseCase(currentCurrency.letterCode)
            val calendar = Calendar.getInstance()

            val currentBalance =
                currencyFormat(allTransactions.sumOf(::calculateBalance).toDouble() / 100)

            val transactionsByMonth = allTransactions.filter { transaction ->
                calendar.timeInMillis = transaction.timestamp
                calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
            }

            val listByPrevMonth = allTransactions.filter { transaction ->
                calendar.timeInMillis = transaction.timestamp
                calendar.get(Calendar.MONTH) < currentCalendar.get(Calendar.MONTH)
            }

            val previousBalance =
                currencyFormat(listByPrevMonth.sumOf(::calculateBalance).toDouble() / 100)

            val (incomeTransactionsByMonth, expenseTransactionsByMonth) = transactionsByMonth
                .partition { it.type == TypeCategory.Income }

            val incomeByMonth = incomeTransactionsByMonth.sumOf { it.amount }.toDouble() / 100
            val expenseByMonth = expenseTransactionsByMonth.sumOf { it.amount }.toDouble() / 100
            val expenseIncome = incomeByMonth - expenseByMonth

            val incomeByMonthFormat = currencyFormat(incomeByMonth)
            val expenseByMonthFormat = currencyFormat(expenseByMonth)
            val expenseIncomeFormat = currencyFormat(expenseIncome)

            allTransactionListUi = transactionsByMonth.map {
                mapTransactionToUi(it, currencyFormat)
            }

            val (incomeTransaction, expenseTransaction) = allTransactionListUi
                .partition { it.type == TypeTransaction.Income }

            incomeTransactionListUi = incomeTransaction
            expenseTransactionListUi = expenseTransaction

            uiState = uiState.copy(
                transactionList = allTransactionListUi,
                currentBalance = currentBalance,
                expense = expenseByMonthFormat,
                income = incomeByMonthFormat,
                expenseIncome = expenseIncomeFormat,
                previousBalance = previousBalance,
                expenseList = allCategories
                    .filter { category -> category.type == TypeCategory.Expense }
                    .map { it.mapToCategoryUi() },
                incomeList = allCategories
                    .filter { category -> category.type == TypeCategory.Income }
                    .map { it.mapToCategoryUi() },
            )
        }.launchIn(viewModelScope)
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(date)

    private fun calculateBalance(transaction: TransactionWithCategory) =
        if (transaction.type == TypeCategory.Expense) {
            -transaction.amount
        } else {
            transaction.amount
        }

    private fun mapTransactionToUi(
        transactionWithCategory: TransactionWithCategory,
        format: (Double) -> String
    ) = TransactionListUi(
        amount = format(transactionWithCategory.amount / 100.0),
        note = transactionWithCategory.note,
        type = if (transactionWithCategory.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income,
        category = transactionWithCategory.name,
        icon = transactionWithCategory.icon,
    )
}