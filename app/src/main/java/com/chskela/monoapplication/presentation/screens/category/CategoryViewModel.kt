package com.chskela.monoapplication.presentation.screens.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUi
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCases: CategoryUseCases) : ViewModel() {

    var uiState : MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
    private set

    init {
        getCategoryList()
    }

    fun add(category:Category) {
        viewModelScope.launch {
            categoryUseCases.addCategoryUseCase(category)
        }
    }

    fun onEvent(event: CategoryEvent) {
        when(event) {
            is CategoryEvent.AddMore -> {

            }
            is CategoryEvent.EditCategory -> {

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
       }.launchIn(viewModelScope)
    }
}