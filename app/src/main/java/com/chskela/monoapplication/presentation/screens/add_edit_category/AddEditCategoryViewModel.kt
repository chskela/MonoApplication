package com.chskela.monoapplication.presentation.screens.add_edit_category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.data.icons.iconsMap
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val icons = iconsMap.toList()
        .mapIndexed { index, icon ->
            CategoryUi(
                id = index.toLong(),
                icon = icon.first,
            )
        }

    var uiState: MutableState<AddEditCategoryUiState> = mutableStateOf(
        AddEditCategoryUiState(
            icons = icons
        )
    )
        private set

    init {
        savedStateHandle.get<Long>("categoryId")?.let { categoryId ->
            if (categoryId != -1L) {
                onEvent(AddEditCategoryEvent.GetCategory(categoryId))
            }
        }
    }


    fun onEvent(eventEdit: AddEditCategoryEvent) {
        when (eventEdit) {
            is AddEditCategoryEvent.SelectTab -> {
                val typeCategory =
                    if (eventEdit.tab == 0) TypeCategory.Expense else TypeCategory.Income
                uiState.value = uiState.value.copy(
                    currentTab = eventEdit.tab,
                    typeCategory = typeCategory
                )
            }

            is AddEditCategoryEvent.ChangeCategoryName -> {
                uiState.value = uiState.value.copy(categoryName = eventEdit.value)
            }

            is AddEditCategoryEvent.ChangeCategoryIcon -> {
                val icon = icons[eventEdit.iconId.toInt()].icon
                icon?.let {
                    uiState.value = uiState.value.copy(icon = icon)
                }
            }

            is AddEditCategoryEvent.GetCategory -> {
                uiState.value = uiState.value.copy(isNewCategory = false)
                viewModelScope.launch {
                    val category = categoryUseCases.getCategoryByIdUseCase(eventEdit.categoryId)
                    uiState.value = uiState.value.copy(
                        id = category.id,
                        categoryName = category.name,
                        icon = category.icon,
                        typeCategory = category.type,
                    )
                }
            }

            is AddEditCategoryEvent.UpdateCategory -> {
                viewModelScope.launch {
                    categoryUseCases.updateCategoryUseCase(
                        Category(
                            id = uiState.value.id,
                            name = uiState.value.categoryName,
                            icon = uiState.value.icon,
                            type = uiState.value.typeCategory,
                        )
                    )
                }
            }

            is AddEditCategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addCategoryUseCase(
                        Category(
                            id = 0,
                            name = uiState.value.categoryName,
                            icon = uiState.value.icon,
                            type = uiState.value.typeCategory
                        )
                    )
                }
            }
        }
    }
}