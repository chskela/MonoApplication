package com.chskela.monoapplication.presentation.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.AddCategoryUseCase
import com.chskela.monoapplication.domain.category.usecase.DeleteCategoryUseCase
import com.chskela.monoapplication.domain.category.usecase.GetAllCategoryByTypeUseCase
import com.chskela.monoapplication.domain.category.usecase.GetCategoryByIdUseCase
import com.chskela.monoapplication.mappers.mapToCategoryUi
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getAllCategoryByTypeUseCase: GetAllCategoryByTypeUseCase,
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()

    private var restoreCategory: Category? = null

    init {
        initialUiState()
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    restoreCategory = getCategoryByIdUseCase(event.id).first()
                    deleteCategoryUseCase(event.id)
                }
            }
            CategoryEvent.Restore -> {
                viewModelScope.launch {
                    addCategoryUseCase(restoreCategory ?: return@launch)
                    restoreCategory = null
                }
            }
        }
    }

    private fun initialUiState() {
        val expenseListFlow = getListOfCategoryByType(TypeCategory.Expense)
        val incomeListFlow = getListOfCategoryByType(TypeCategory.Income)

        combine(expenseListFlow, incomeListFlow) { expenseList, incomeList ->
            _uiState.update {
                it.copy(expenseList = expenseList, incomeList = incomeList)
            }
        }.launchIn(viewModelScope)
    }

    private fun getListOfCategoryByType(type: TypeCategory) = getAllCategoryByTypeUseCase(type)
        .map { list ->
            list.map { it.mapToCategoryUi() }
        }
}