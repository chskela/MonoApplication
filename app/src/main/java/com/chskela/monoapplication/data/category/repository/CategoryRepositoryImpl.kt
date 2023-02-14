package com.chskela.monoapplication.data.category.repository

import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.mappers.mapToDomain
import com.chskela.monoapplication.mappers.mapToData
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
            .map { list -> list.map { it.mapToDomain() } }
            .flowOn(coroutineContext)
    }

    override fun getAllCategoryByType(type: TypeCategory): Flow<List<Category>> {
        return categoryDao.getAllCategoryByType(type = type.mapToData())
            .distinctUntilChanged()
            .map { list -> list.map { it.mapToDomain() } }
            .flowOn(coroutineContext)
    }

    override fun getCategoryById(id: Long): Flow<Category> {
        return categoryDao.getCategoryById(id)
            .distinctUntilChanged()
            .map { it.mapToDomain() }
            .flowOn(coroutineContext)
    }

    override suspend fun insertCategory(category: Category) {
        withContext(coroutineContext) {
            categoryDao.insertCategory(category.mapToData())
        }
    }

    override suspend fun updateCategory(category: Category) {
        withContext(coroutineContext) {
            categoryDao.updateCategory(category.mapToData())
        }
    }

    override suspend fun deleteCategory(id: Long) {
        withContext(coroutineContext) {
            categoryDao.deleteCategory(id)
        }
    }
}