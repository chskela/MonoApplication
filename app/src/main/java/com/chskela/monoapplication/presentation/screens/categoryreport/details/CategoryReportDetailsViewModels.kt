package com.chskela.monoapplication.presentation.screens.categoryreport.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.presentation.screens.categoryreport.details.models.CategoryReportDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryReportDetailsViewModels @Inject constructor(
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
        }
    }
}