package com.chskela.monoapplication.presentation.screens.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.domain.category.usecase.GetAllCategoryByTypeUseCase
import com.chskela.monoapplication.mappers.mapToCategoryUi
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    private val getAllCategoryByTypeUseCase: GetAllCategoryByTypeUseCase,
) :
    ViewModel() {

    var uiState: MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
        private set

    private var restoreCategory: Category? = null

    init {
        initialUiState()
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    restoreCategory = categoryUseCases.getCategoryByIdUseCase(event.id).first()
                    categoryUseCases.deleteCategoryUseCase(event.id)
                }
            }
            CategoryEvent.Restore -> {
                viewModelScope.launch {
                    categoryUseCases.addCategoryUseCase(restoreCategory ?: return@launch)
                    restoreCategory = null
                }
            }
        }
    }

    private fun initialUiState() {
        val expenseListFlow = getListOfCategoryByType(TypeCategory.Expense)
        val incomeListFlow = getListOfCategoryByType(TypeCategory.Income)

        combine(expenseListFlow, incomeListFlow) { expenseList, incomeList ->
            uiState.value = uiState.value.copy(
                expenseList = expenseList,
                incomeList = incomeList,
            )
        }.launchIn(viewModelScope)
    }

    private fun getListOfCategoryByType(type: TypeCategory) = getAllCategoryByTypeUseCase(type)
        .map { list ->
            list.map { it.mapToCategoryUi() }
        }
}