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
import com.chskela.monoapplication.presentation.screens.add_edit_category.models.AddAndEditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    var uiState: MutableState<AddAndEditCategoryUiState> = mutableStateOf(
        AddAndEditCategoryUiState(
            icons = icons
        )
    )
        private set

    init {
        savedStateHandle.get<Long>("categoryId")?.let { categoryId ->
            if (categoryId != -1L) {
                onEvent(AddAndEditCategoryEvent.GetCategoryAnd(categoryId))
            }
        }
    }


    fun onEvent(eventEdit: AddAndEditCategoryEvent) {
        when (eventEdit) {
            is AddAndEditCategoryEvent.SelectTab -> {
                val typeCategory =
                    if (eventEdit.tab == 0) TypeCategory.Expense else TypeCategory.Income
                uiState.value = uiState.value.copy(
                    currentTab = eventEdit.tab,
                    typeCategory = typeCategory
                )
            }

            is AddAndEditCategoryEvent.ChangeCategoryNameAnd -> {
                uiState.value = uiState.value.copy(categoryName = eventEdit.value)
            }

            is AddAndEditCategoryEvent.ChangeCategoryIconAnd -> {
                val icon = icons[eventEdit.iconId.toInt()].icon
                icon?.let {
                    uiState.value = uiState.value.copy(currentIcon = icon)
                }
            }

            is AddAndEditCategoryEvent.GetCategoryAnd -> {
                categoryUseCases.getCategoryByIdUseCase(eventEdit.categoryId)
                    .flowOn(Dispatchers.IO)
                    .onEach { category ->
                        uiState.value = uiState.value.copy(
                            isNewCategory = false,
                            categoryId = category.id,
                            categoryName = category.name,
                            currentIcon = category.icon,
                            typeCategory = category.type,
                        )
                    }.launchIn(viewModelScope)
            }

            is AddAndEditCategoryEvent.UpdateCategoryAnd -> {
                viewModelScope.launch(Dispatchers.IO) {
                    categoryUseCases.updateCategoryUseCase(
                        Category(
                            id = uiState.value.categoryId,
                            name = uiState.value.categoryName,
                            icon = uiState.value.currentIcon,
                            type = uiState.value.typeCategory,
                        )
                    )
                }
            }

            is AddAndEditCategoryEvent.AddAndCategory -> {
                viewModelScope.launch(Dispatchers.IO) {
                    categoryUseCases.addCategoryUseCase(
                        Category(
                            id = 0,
                            name = uiState.value.categoryName,
                            icon = uiState.value.currentIcon,
                            type = uiState.value.typeCategory
                        )
                    )
                }
            }
        }
    }
}