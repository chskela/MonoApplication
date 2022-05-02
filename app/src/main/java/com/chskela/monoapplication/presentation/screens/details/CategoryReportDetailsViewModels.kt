package com.chskela.monoapplication.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.details.models.CategoryReportDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryReportDetailsViewModels @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    var uiState: MutableState<CategoryReportDetailsUiState> =
        mutableStateOf(CategoryReportDetailsUiState())
        private set

    fun onEvent(event: CategoryReportDetailsEvent) {
        when (event) {

            is CategoryReportDetailsEvent.SelectTab -> {
                uiState.value = uiState.value.copy(
                    currentTab = event.tab,
                )
            }

            is CategoryReportDetailsEvent.GetCategory -> {
                viewModelScope.launch {
                    val category = categoryUseCases.getCategoryByIdUseCase(event.categoryId)
                    uiState.value = uiState.value.copy(
                        currentCategory = category.id,
                        categoryName = category.name,
                        icon = category.icon,
                        typeCategory = category.type,
                    )
                }
            }
        }
    }
}