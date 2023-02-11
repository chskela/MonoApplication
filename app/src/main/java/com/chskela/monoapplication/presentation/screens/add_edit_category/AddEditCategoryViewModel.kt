package com.chskela.monoapplication.presentation.screens.add_edit_category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.AddCategoryUseCase
import com.chskela.monoapplication.domain.category.usecase.GetCategoryByIdUseCase
import com.chskela.monoapplication.domain.category.usecase.UpdateCategoryUseCase
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddAndEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) :
    ViewModel() {

    private val icons = iconsMap.toList()
        .mapIndexed { index, icon ->
            CategoryUi(
                id = index.toLong(),
                icon = icon.first,
            )
        }

    var uiState: AddAndEditCategoryUiState by mutableStateOf(
        AddAndEditCategoryUiState(
            icons = icons
        )
    )
        private set

    init {
        savedStateHandle.get<Long>("categoryId")?.let { categoryId ->
            if (categoryId != -1L) {
                uiState = uiState.copy(isEnableButton = true)
                onEvent(AddAndEditCategoryEvent.GetCategory(categoryId))
            }
        }
    }


    fun onEvent(eventEdit: AddAndEditCategoryEvent) {
        when (eventEdit) {
            is AddAndEditCategoryEvent.SelectTab -> {
                val typeCategory =
                    if (eventEdit.tab == 0) TypeCategory.Expense else TypeCategory.Income
                uiState = uiState.copy(
                    currentTab = eventEdit.tab,
                    typeCategory = typeCategory
                )
            }

            is AddAndEditCategoryEvent.ChangeCategoryName -> {
                uiState = uiState.copy(categoryName = eventEdit.value)
                uiState = uiState.copy(isEnableButton = isEnable(uiState))
            }

            is AddAndEditCategoryEvent.ChangeCategoryIcon -> {
                val icon = icons[eventEdit.iconId.toInt()].icon
                uiState = uiState.copy(currentIcon = icon)
                uiState = uiState.copy(isEnableButton = isEnable(uiState))
            }

            is AddAndEditCategoryEvent.GetCategory -> {
                getCategoryByIdUseCase(eventEdit.categoryId)
                    .onEach { (id, name, icon, type) ->
                        uiState = uiState.copy(
                            isNewCategory = false,
                            categoryId = id,
                            categoryName = name,
                            currentIcon = icon,
                            typeCategory = type,
                        )
                    }.launchIn(viewModelScope)
            }

            is AddAndEditCategoryEvent.UpdateCategoryAnd -> {
                viewModelScope.launch {
                    updateCategoryUseCase(
                        Category(
                            id = uiState.categoryId,
                            name = uiState.categoryName,
                            icon = uiState.currentIcon,
                            type = uiState.typeCategory,
                        )
                    )
                }
            }

            is AddAndEditCategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    addCategoryUseCase(
                        Category(
                            id = 0,
                            name = uiState.categoryName,
                            icon = uiState.currentIcon,
                            type = uiState.typeCategory
                        )
                    )
                }
            }
        }
    }

    private fun isEnable(uiState: AddAndEditCategoryUiState): Boolean =
        validateCategoryName(uiState.categoryName) && validateCategoryIcon(uiState.currentIcon)

    private fun validateCategoryName(name: String): Boolean = name.isNotBlank()

    private fun validateCategoryIcon(icon: String): Boolean = icon.isNotBlank()
}