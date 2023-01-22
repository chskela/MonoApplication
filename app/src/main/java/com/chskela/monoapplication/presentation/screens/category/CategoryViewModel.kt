package com.chskela.monoapplication.presentation.screens.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCases: CategoryUseCases) :
    ViewModel() {

    var uiState: MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
        private set

    private var restoreCategory: Category? = null

    init {
        getCategoryList()
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch(Dispatchers.IO) {
                    restoreCategory = categoryUseCases.getCategoryByIdUseCase(event.id).first()
                    categoryUseCases.deleteCategoryUseCase(event.id)
                }
            }
            CategoryEvent.Restore -> {
                viewModelScope.launch(Dispatchers.IO) {
                    categoryUseCases.addCategoryUseCase(restoreCategory ?: return@launch)
                    restoreCategory = null
                }
            }
        }
    }

    private fun getCategoryList() {
        categoryUseCases.getAllCategoryUseCase()
            .flowOn((Dispatchers.IO))
            .onEach {
                uiState.value = uiState.value.copy(
                    expenseList = it
                        .filter { category -> category.type == TypeCategory.Expense }
                        .map { item ->
                            CategoryUi(
                                id = item.id,
                                icon = item.icon,
                                title = item.name
                            )
                        },
                    incomeList = it
                        .filter { category -> category.type == TypeCategory.Income }
                        .map { item ->
                            CategoryUi(
                                id = item.id,
                                icon = item.icon,
                                title = item.name
                            )
                        },
                )
            }
            .catch { e -> Log.e("RESULT", "getCategoryList: $e") }
            .launchIn(viewModelScope)
    }
}