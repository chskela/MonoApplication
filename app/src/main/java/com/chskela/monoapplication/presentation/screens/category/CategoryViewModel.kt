package com.chskela.monoapplication.presentation.screens.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCases: CategoryUseCases) :
    ViewModel() {

    var uiState : MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
    private set

    init {
        getCategoryList()
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.DeleteCategory -> {
                viewModelScope.launch {
                    categoryUseCases.deleteCategoryUseCase(event.id)
                }
            }
        }
    }

    private fun getCategoryList() {
        categoryUseCases.getAllCategoryUseCase().onEach {
            uiState.value = uiState.value.copy(
                expenseList = it
                    .filter { category -> category.type == TypeCategory.Expense }
                    .map { item -> CategoryUi(id = item.id, icon = item.icon, title = item.name) },
                incomeList = it
                    .filter { category -> category.type == TypeCategory.Income }
                    .map { item -> CategoryUi(id = item.id, icon = item.icon, title = item.name) },
            )
        }
            .catch { e -> Log.e("RESULT", "getCategoryList: $e") }
            .launchIn(viewModelScope)
    }
}