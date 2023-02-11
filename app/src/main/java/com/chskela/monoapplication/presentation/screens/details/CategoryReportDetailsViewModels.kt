package com.chskela.monoapplication.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
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

    var uiState: CategoryReportDetailsUiState by mutableStateOf(CategoryReportDetailsUiState())

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
                uiState = uiState.copy(
                    currentTab = event.tab,
                )
            }

            is CategoryReportDetailsEvent.GetData -> {
                combine(
                    getCategoryByIdUseCase(event.categoryId),
                    getAllTransactionsByMonthAndCategoryUseCase(
                        categoryId = event.categoryId,
                        month = currentCalendar.get(Calendar.MONTH)
                    ),
                    getAmountByCategoryPerMonthUseCase(event.categoryId),
                    getDefaultCurrencyUseCase(),
                ) { category, list, sumThisMonth, currentCurrency ->
                    val currencyFormat = currencyFormatUseCase(currentCurrency.letterCode)
                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.MONTH, -1)
                    calendar.get(Calendar.MONTH)

//                    val reportsList: List<ReportUi> =
                    uiState = uiState.copy(
                        categoryName = category.name,
                        icon = category.icon,
                        typeCategory = category.type,
                        sumThisMonth = currencyFormat(sumThisMonth),
                        transactionList = list.map {
                            TransactionListUi(
                                amount = currencyFormat(it.amount / 100.0),
                                note = it.note,
                                type = if (category.type == TypeCategory.Expense) TypeTransaction.Expense else TypeTransaction.Income,
                                category = category.name,
                                icon = null
                            )
                        }
                    )
                }.launchIn(viewModelScope)
            }
        }
    }
}