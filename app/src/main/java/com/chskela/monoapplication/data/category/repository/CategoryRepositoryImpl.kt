package com.chskela.monoapplication.data.category.repository

import com.chskela.monoapplication.data.category.storage.dao.CategoryDao
import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import com.chskela.monoapplication.data.category.storage.models.Type
import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override fun getAllCategory(): Flow<List<Category>> {
        return categoryDao.getAllCategory().map { list ->
            list.map { mapCategoryToDomain(it) }
        }
    }

    override fun getCategoryById(id: Long): Flow<Category> {
        return categoryDao.getCategoryById(id).map { categoryEntity ->
            mapCategoryToDomain(categoryEntity)
        }
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(mapCategoryToStorage(category))
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(mapCategoryToStorage(category))
    }

    override suspend fun deleteCategory(id: Long) {
        categoryDao.deleteCategory(id)
    }

    private fun mapTypeToDomain(type: Type): TypeCategory = when (type) {
        Type.Expense -> TypeCategory.Expense
        Type.Income -> TypeCategory.Income
    }

    private fun mapTypeToStorage(type: TypeCategory): Type = when (type) {
        TypeCategory.Expense -> Type.Expense
        TypeCategory.Income -> Type.Income
    }

    private fun mapCategoryToDomain(categoryEntity: CategoryEntity): Category = Category(
        id = categoryEntity.id ?: 0,
        name = categoryEntity.name,
        icon = categoryEntity.icon,
        type = mapTypeToDomain(categoryEntity.type)
    )

    private fun mapCategoryToStorage(category: Category): CategoryEntity = if (category.id == 0L) {
        CategoryEntity(
            name = category.name,
            icon = category.icon,
            type = mapTypeToStorage(category.type)
        )
    } else CategoryEntity(
        id = category.id,
        name = category.name,
        icon = category.icon,
        type = mapTypeToStorage(category.type)
    )
}