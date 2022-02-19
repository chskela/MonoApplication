package com.chskela.monoapplication.domain.category.repository

import com.chskela.monoapplication.domain.category.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategory() : Flow<List<Category>>

    suspend fun getCategoryById(id: Long) : Category

    suspend fun insertCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)
}