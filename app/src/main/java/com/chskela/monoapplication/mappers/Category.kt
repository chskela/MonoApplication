package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

fun Category.mapCategoryToUi() = CategoryUi(id = id, icon = icon, title = name)