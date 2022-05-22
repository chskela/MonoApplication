package com.chskela.monoapplication.domain.category.repository

import com.chskela.monoapplication.domain.category.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategory() : Flow<List<Category>>

    fun getCategoryById(id: Long) : Flow<Category>

    suspend fun insertCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(id: Long)
}