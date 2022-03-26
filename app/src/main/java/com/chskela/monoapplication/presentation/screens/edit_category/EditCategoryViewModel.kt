package com.chskela.monoapplication.presentation.screens.edit_category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chskela.monoapplication.R
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.usecase.CategoryUseCases
import com.chskela.monoapplication.presentation.screens.edit_category.models.EditCategoryUiState
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(private val categoryUseCases: CategoryUseCases) :
    ViewModel() {

    var uiState: MutableState<EditCategoryUiState> = mutableStateOf(EditCategoryUiState(
        icons = icons.mapIndexed { index, icon ->
            CategoryUi(
                id = index.toLong(),
                icon = icon,
            )
        }
    ))
        private set

    fun onEvent(eventEdit: EditCategoryEvent) {
        when (eventEdit) {
            is EditCategoryEvent.SelectTab -> {
                uiState.value = uiState.value.copy(currentTab = eventEdit.tab)
            }
            is EditCategoryEvent.ChangeCategoryName -> {
                uiState.value = uiState.value.copy(categoryName = eventEdit.value)
            }

            is EditCategoryEvent.ChangeCategoryIcon -> {
                uiState.value = uiState.value.copy(icon = eventEdit.iconId.toInt())
            }

            is EditCategoryEvent.GetCategory -> {
                viewModelScope.launch {
                    val category = categoryUseCases.getCategoryByIdUseCase(eventEdit.categoryId)
                    uiState.value = uiState.value.copy(
                        id = category.id,
                        categoryName = category.name,
                        icon = category.icon,
                        typeCategory = category.type
                    )
                }
            }

            is EditCategoryEvent.EditCategory -> {
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

            is EditCategoryEvent.AddCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addCategoryUseCase(
                        Category(
                            id = 0,
                            name = uiState.value.categoryName,
                            icon = uiState.value.icon
                        )
                    )
                }
            }
        }
    }

}

val icons = listOf(
    R.drawable.category_baby,
    R.drawable.category_bag,
    R.drawable.category_bank,
    R.drawable.category_bird,
    R.drawable.category_birthday,
    R.drawable.category_boat,
    R.drawable.category_books,
    R.drawable.category_brain,
    R.drawable.category_building,
    R.drawable.category_camera,
    R.drawable.category_car,
    R.drawable.category_cat,
    R.drawable.category_chair,
    R.drawable.category_challenge,
    R.drawable.category_clothes,
    R.drawable.category_coffer,
    R.drawable.category_coin,
    R.drawable.category_cook,
    R.drawable.category_cup,
    R.drawable.category_currency,
    R.drawable.category_cycles,
    R.drawable.category_dinner,
    R.drawable.category_dog,
    R.drawable.category_face_mask,
    R.drawable.category_fix,
    R.drawable.category_flower,
    R.drawable.category_food,
    R.drawable.category_football,
    R.drawable.category_fun,
    R.drawable.category_gas,
    R.drawable.category_gift,
    R.drawable.category_glass,
    R.drawable.category_gym,
    R.drawable.category_house,
    R.drawable.category_lawyer,
    R.drawable.category_map,
    R.drawable.category_market,
    R.drawable.category_medicine,
    R.drawable.category_money,
    R.drawable.category_music,
    R.drawable.category_package,
    R.drawable.category_party,
    R.drawable.category_pie,
    R.drawable.category_pill,
    R.drawable.category_plane,
    R.drawable.category_receipt,
    R.drawable.category_run,
    R.drawable.category_sea,
    R.drawable.category_shower,
    R.drawable.category_store,
    R.drawable.category_study,
    R.drawable.category_tennis_ball,
    R.drawable.category_train,
    R.drawable.category_user,
    R.drawable.category_wallet,
    R.drawable.category_wc,
)