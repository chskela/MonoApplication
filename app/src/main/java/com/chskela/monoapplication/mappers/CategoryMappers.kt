package com.chskela.monoapplication.mappers

import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.presentation.ui.components.categorysurface.CategoryUi

fun Category.mapToCategoryUi(): CategoryUi = CategoryUi(id = id, icon = icon, title = name)

fun Category.mapToData(): CategoryEntity = CategoryEntity(
    id = id,
    name = name,
    icon = icon,
    type = type.mapToData()
)

fun CategoryEntity.mapToDomain(): Category = Category(
    id = id,
    name = name,
    icon = icon,
    type = type.mapToDomain()
)