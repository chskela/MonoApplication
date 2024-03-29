package com.chskela.monoapplication.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.GetCategoryByIdUseCase
import com.chskela.monoapplication.domain.common.usecase.CurrencyFormatUseCase
import com.chskela.monoapplication.domain.currency.usecase.GetDefaultCurrencyUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsOfCategoryInThisMonthUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAmountByCategoryPerMonthUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAmountByMonthsInCategoryMonthUseCase
import com.chskela.monoapplication.presentation.screens.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.screens.details.models.ReportChartUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TransactionListUi
import com.chskela.monoapplication.presentation.ui.components.transactionList.model.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CategoryReportDetailsViewModels @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val currencyFormatUseCase: CurrencyFormatUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getDefaultCurrencyUseCase: GetDefaultCurrencyUseCase,
    private val getAmountByCategoryPerMonthUseCase: GetAmountByCategoryPerMonthUseCase,
    private val getAmountByMonthsInCategoryMonthUseCase: GetAmountByMonthsInCategoryMonthUseCase,
    private val getAllTransactionsOfCategoryInThisMonthUseCase: GetAllTransactionsOfCategoryInThisMonthUseCase
) : ViewModel() {

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

                combine(
                    getDefaultCurrencyUseCase(),
                    getCategoryByIdUseCase(categoryId),
                    getAmountByCategoryPerMonthUseCase(categoryId),
                    getAmountByMonthsInCategoryMonthUseCase(categoryId),
                    getAllTransactionsOfCategoryInThisMonthUseCase(categoryId)
                ) { currentCurrency, category, sumThisMonth, report, list ->
                    val currencyFormat = currencyFormatUseCase(currentCurrency.letterCode)

                    _uiState.update {
                        it.copy(
                            categoryName = category.name,
                            icon = category.icon,
                            typeCategory = category.type,
                            sumThisMonth = currencyFormat(sumThisMonth),
                            transactionList = list.map { transition ->
                                TransactionListUi(
                                    amount = currencyFormat(transition.amount / 100.0),
                                    note = transition.note,
                                    type = getTypeTransaction(category),
                                    category = category.name,
                                    icon = null
                                )
                            },
                            reportsList = report.map { l -> ReportChartUi(l.first, l.second) }
                        )
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun getTypeTransaction(category: Category) =
        if (category.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income
}