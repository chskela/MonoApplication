package com.chskela.monoapplication.data.category.repository

import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.mappers.mapToCategory
import com.chskela.monoapplication.mappers.mapToCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return categoryDao.getAllCategory()
            .distinctUntilChanged()
            .map { list ->
                list.map { it.mapToCategory() }
            }
    }

    override suspend fun getCategoryById(id: Long): Flow<Category> {
        return categoryDao.getCategoryById(id)
            .distinctUntilChanged()
            .map { it.mapToCategory() }
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category.mapToCategoryEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.mapToCategoryEntity())
    }

    override suspend fun deleteCategory(id: Long) {
        categoryDao.deleteCategory(id)
    }
}