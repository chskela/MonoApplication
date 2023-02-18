package com.chskela.monoapplication.presentation.screens.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.GetAllCategoryByTypeUseCase
import com.chskela.monoapplication.domain.currency.models.Currency
import com.chskela.monoapplication.domain.currency.usecase.GetDefaultCurrencyUseCase
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.usercase.AddTransactionUseCase
import com.chskela.monoapplication.mappers.mapToCategoryUi
import com.chskela.monoapplication.presentation.screens.transaction.models.TransitionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TransitionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getAllCategoryByTypeUseCase: GetAllCategoryByTypeUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase
) : ViewModel() {

    private var currentDate = Calendar.getInstance()

    private val expenseListFlow = getListOfCategoryByType(TypeCategory.Expense)
    private val incomeListFlow = getListOfCategoryByType(TypeCategory.Income)

    init {
        initialUiState()
    }

    private val _uiState = MutableStateFlow(
        TransitionUiState(
            currentData = formatDate(currentDate.time),
            currentCurrency = Currency(
                id = 1,
                name = "Ruble",
                letterCode = "RUB",
                symbol = "₽",
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TransitionEvent) {
        when (event) {
            is TransitionEvent.SelectTab -> {
                combine(expenseListFlow, incomeListFlow) { expense, income ->
                    _uiState.update {
                        it.copy(
                            currentTab = event.tab,
                            listCategory = if (event.tab == 0) expense else income
                        )
                    }
                }.launchIn(viewModelScope)
            }

            is TransitionEvent.ChangeAmount -> {
                if (validateAmount(event.value)) {
                    _uiState.update { it.copy(amount = event.value) }
                    _uiState.update { it.copy(isEnabledButton = isEnabled()) }
                }
            }

            is TransitionEvent.ChangeNote -> _uiState.update { it.copy(note = event.value) }

            is TransitionEvent.SelectCategory -> {
                _uiState.update { it.copy(currentCategory = event.categoryId) }
                _uiState.update { it.copy(isEnabledButton = isEnabled()) }
            }

            is TransitionEvent.Submit -> viewModelScope.launch {
                val createdAt = Calendar.getInstance()
                addTransactionUseCase(
                    Transaction(
                        id = 0,
                        timestamp = createdAt.time,
                        amount = (_uiState.value.amount.toDouble() * 100).toLong(),
                        note = _uiState.value.note,
                        categoryId = _uiState.value.currentCategory,
                    )
                )
                _uiState.update {
                    it.copy(
                        amount = "",
                        note = "",
                        currentCategory = -1L,
                        isEnabledButton = false
                    )
                }
            }

            is TransitionEvent.PreviousDate -> changeDate(-1)

            is TransitionEvent.NextDate -> changeDate(1)
        }
    }

    private fun changeDate(amount: Int) {
        currentDate.add(Calendar.DAY_OF_MONTH, amount)
        _uiState.update { it.copy(currentData = formatDate(currentDate.time)) }
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

    private fun initialUiState() {
        combine(expenseListFlow, getDefaultCurrencyUseCase()) { expense, currency ->
            _uiState.update {
                it.copy(listCategory = expense, currentCurrency = currency)
            }
        }.launchIn(viewModelScope)
    }

    private fun isEnabled() = with(uiState.value) {
        amount.isNotEmpty() && currentCategory != -1L
    }

    private fun formatDate(date: Date) =
        SimpleDateFormat("dd MMMM yyyy (EEEE)", Locale.getDefault()).format(date)

    private fun getListOfCategoryByType(type: TypeCategory) = getAllCategoryByTypeUseCase(type)
        .map { list -> list.map { it.mapToCategoryUi() } }
}