package com.chskela.monoapplication.presentation.screens.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chskela.monoapplication.presentation.screens.category.models.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : ViewModel() {

    var uiState : MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
    private set
}