package com.chskela.monoapplication.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsByMonthAndCategoryUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAllTransactionsUseCase
import com.chskela.monoapplication.domain.reports.usecase.GetAmountByCategoryPerMonthUseCase
import com.chskela.monoapplication.presentation.screens.details.models.CategoryReportDetailsUiState
import com.chskela.monoapplication.presentation.screens.reports.models.TransactionUi
import com.chskela.monoapplication.presentation.screens.reports.models.TypeTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CategoryReportDetailsViewModels @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val getAllTransactionsByMonthAndCategoryUseCase: GetAllTransactionsByMonthAndCategoryUseCase,
    private val getAmountByCategoryPerMonthUseCase: GetAmountByCategoryPerMonthUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var currentCalendar = Calendar.getInstance()

    var uiState: MutableState<CategoryReportDetailsUiState> =
        mutableStateOf(CategoryReportDetailsUiState())
        private set

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
                uiState.value = uiState.value.copy(
                    currentTab = event.tab,
                )
            }

            is CategoryReportDetailsEvent.GetData -> {
                combine(
//                    getAllTransactionsUseCase(),
                    categoryUseCases.getCategoryByIdUseCase(event.categoryId),
                    getAllTransactionsByMonthAndCategoryUseCase(
                        categoryId = event.categoryId,
                        month = currentCalendar.get(Calendar.MONTH)
                    ),
                    getAmountByCategoryPerMonthUseCase(event.categoryId)
                ) { category, list, sumThisMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.MONTH, -1)
                    calendar.get(Calendar.MONTH)

//                    val reportsList: List<ReportUi> =
                    uiState.value = uiState.value.copy(
                        currentCategory = category.id,
                        categoryName = category.name,
                        icon = category.icon,
                        typeCategory = category.type,
                        sumThisMonth = sumThisMonth,
                        transactionList = list.map {
                            TransactionUi(
                                id = it.id,
                                timestamp = it.timestamp,
                                amount = it.amount / 100.0,
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