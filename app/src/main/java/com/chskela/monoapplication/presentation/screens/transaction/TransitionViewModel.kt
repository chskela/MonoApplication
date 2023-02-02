package com.chskela.monoapplication.presentation.screens.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.usecase.CurrencyUseCases
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.usercase.TransactionUseCases
import com.chskela.monoapplication.mappers.mapToCategoryUi
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransitionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases,
    private val categoryUseCases: CategoryUseCases,
    private val currencyUseCases: CurrencyUseCases
) : ViewModel() {

    private var currentDate = Calendar.getInstance()
    private var expenseList: List<CategoryUi> = emptyList()
    private var incomeList: List<CategoryUi> = emptyList()

    init {
        initialUiStateFromStore()
    }

    var uiState: TransitionUiState by mutableStateOf(
        TransitionUiState(
            currentData = formatDate(currentDate.time),
            listCategory = expenseList,
            currentCurrency = Currency(
                id = 1,
                name = "Ruble",
                letterCode = "RUB",
                symbol = "₽",
            )
        )
    )

    fun onEvent(event: TransitionEvent) {
        when (event) {
            is TransitionEvent.SelectTab -> {
                uiState = uiState.copy(
                    currentTab = event.tab,
                    listCategory = if (event.tab == 0) expenseList else incomeList
                )
            }
            is TransitionEvent.ChangeAmount -> {
                if (validateAmount(event.value)) {
                    uiState = uiState.copy(amount = event.value)
                    uiState = uiState.copy(isEnabledButton = isEnabled())
                }
            }
            is TransitionEvent.ChangeNote -> {
                uiState = uiState.copy(note = event.value)
            }
            is TransitionEvent.SelectCategory -> {
                uiState = uiState.copy(currentCategory = event.categoryId)
                uiState = uiState.copy(isEnabledButton = isEnabled())
            }
            is TransitionEvent.Submit -> viewModelScope.launch {
                val createdAt = Calendar.getInstance()
                transactionUseCases.addTransactionUseCase(
                    Transaction(
                        id = 0,
                        timestamp = createdAt.timeInMillis,
                        amount = (uiState.amount.toDouble() * 100).toLong(),
                        note = uiState.note,
                        categoryId = uiState.currentCategory,
                    )
                )
                uiState = uiState.copy(
                    amount = "",
                    note = "",
                    currentCategory = 0,
                    isEnabledButton = false
                )
            }
            is TransitionEvent.PreviousData -> {
                currentDate.add(Calendar.DAY_OF_MONTH, -1)
                uiState = uiState.copy(currentData = formatDate(currentDate.time))
            }
            is TransitionEvent.NextData -> {
                currentDate.add(Calendar.DAY_OF_MONTH, 1)
                uiState = uiState.copy(currentData = formatDate(currentDate.time))
            }
        }
    }

    private fun validateAmount(value: String) =
        value.isBlank() || isNumber(value) && isFraction(value)

    private fun isFraction(value: String): Boolean {
        val partsOfNumber = value.split('.')
        val size = partsOfNumber.size
        return (partsOfNumber.last().length <= 2 && size == 2) || size < 2
    }

    private fun isNumber(value: String): Boolean {
        val symbols = listOf('.').plus('0'..'9')
        return value.last() in symbols && value.first() != '.' && value.take(2) != "00"
    }

    private fun initialUiStateFromStore() {
        categoryUseCases.getAllCategoryUseCase()
            .combine(currencyUseCases.getDefaultCurrencyUseCase()) { list, currency ->
                expenseList = list
                    .filter { category -> category.type == TypeCategory.Expense }
                    .map { it.mapToCategoryUi() }
                    .also { item ->
                        uiState = uiState.copy(
                            listCategory = item,
                            currentCurrency = currency
                        )
                    }

                incomeList = list
                    .filter { category -> category.type == TypeCategory.Income }
                    .map { it.mapToCategoryUi() }
            }
            .launchIn(viewModelScope)
    }

    private fun isEnabled() = with(uiState) {
        amount.isNotEmpty() && currentCategory != 0L
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat("dd MMMM yyyy (EEEE)", Locale.getDefault()).format(date)
}