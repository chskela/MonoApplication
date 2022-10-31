package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

fun Category.mapToCategoryUi(): CategoryUi = CategoryUi(id = id, icon = icon, title = name)

fun Category.mapToCategoryEntity(): CategoryEntity = CategoryEntity(
    id = if (id == 0L) null else id,
    name = name,
    icon = icon,
    type = type.mapToType()
)

fun CategoryEntity.mapToCategory(): Category = Category(
    id = id ?: 0,
    name = name,
    icon = icon,
    type = type.mapToTypeCategory()
)