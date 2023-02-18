package com.chskela.monoapplication.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.GetCategoryByIdUseCase
import com.chskela.monoapplication.domain.common.usecase.CurrencyFormatUseCase
import com.chskela.monoapplication.domain.currency.usecase.GetDefaultCurrencyUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsByMonthAndCategoryUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAmountByCategoryPerMonthUseCase
import com.chskela.monoapplication.presentation.screens.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CategoryReportDetailsViewModels @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val currencyFormatUseCase: CurrencyFormatUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val getAmountByCategoryPerMonthUseCase: GetAmountByCategoryPerMonthUseCase,
    private val getAllTransactionsByMonthAndCategoryUseCase: GetAllTransactionsByMonthAndCategoryUseCase
) : ViewModel() {
    private var currentCalendar = Calendar.getInstance()

    private val _uiState = MutableStateFlow(CategoryReportDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Long>("categoryId")?.let { categoryId ->
            if (categoryId != -1L) {
                onEvent(CategoryReportDetailsEvent.GetData(categoryId))
            }
        }
    }

    fun onEvent(event: CategoryReportDetailsEvent) {
        when (event) {

            is CategoryReportDetailsEvent.SelectTab -> {
                _uiState.update {
                    it.copy(currentTab = event.tab)
                }
            }

            is CategoryReportDetailsEvent.GetData -> {
                val categoryId = event.categoryId
                val month = currentCalendar.get(Calendar.MONTH)

                combine(
                    getCategoryByIdUseCase(event.categoryId),
                    getAllTransactionsByMonthAndCategoryUseCase(categoryId, month),
                    getAmountByCategoryPerMonthUseCase(event.categoryId),
                    getDefaultCurrencyUseCase(),
                ) { category, list, sumThisMonth, currentCurrency ->
                    val currencyFormat = currencyFormatUseCase(currentCurrency.letterCode)
                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.MONTH, -1)
                    calendar.get(Calendar.MONTH)

//                    val reportsList: List<ReportUi> =
                    _uiState.update {
                        it.copy(
                            categoryName = category.name,
                            icon = category.icon,
                            typeCategory = category.type,
                            sumThisMonth = currencyFormat(sumThisMonth),
                            transactionList = list.map {transition ->
                                TransactionListUi(
                                    amount = currencyFormat(transition.amount / 100.0),
                                    note = transition.note,
                                    type = getTypeTransaction(category),
                                    category = category.name,
                                    icon = null
                                )
                            }
                        )
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getTypeTransaction(category: Category) =
        if (category.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income
}