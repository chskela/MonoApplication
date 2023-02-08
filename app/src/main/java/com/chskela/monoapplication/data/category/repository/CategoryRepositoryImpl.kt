package com.chskela.monoapplication.data.category.repository

import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.mappers.mapToCategory
import com.chskela.monoapplication.mappers.mapToCategoryEntity
import com.chskela.monoapplication.mappers.mapToType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository,
    AbstractRepository() {

    override fun getAllCategory(): Flow<List<Category>> {
        return categoryDao.getAllCategory()
            .distinctUntilChanged()
            .map { list -> list.map { it.mapToCategory() } }
            .flowOn(coroutineContext)
    }

    override fun getAllCategoryByType(type: TypeCategory): Flow<List<Category>> {
        return categoryDao.getAllCategoryByType(typeCategory = type.mapToType().name)
            .distinctUntilChanged()
            .map { list -> list.map { it.mapToCategory() } }
            .flowOn(coroutineContext)
    }

    override fun getCategoryById(id: Long): Flow<Category> {
        return categoryDao.getCategoryById(id)
            .distinctUntilChanged()
            .map { it.mapToCategory() }
            .flowOn(coroutineContext)
    }

    override suspend fun insertCategory(category: Category) {
        withContext(coroutineContext) {
            categoryDao.insertCategory(category.mapToCategoryEntity())
        }
    }

    override suspend fun updateCategory(category: Category) {
        withContext(coroutineContext) {
            categoryDao.updateCategory(category.mapToCategoryEntity())
        }
    }

    override suspend fun deleteCategory(id: Long) {
        withContext(coroutineContext) {
            categoryDao.deleteCategory(id)
        }
    }
}