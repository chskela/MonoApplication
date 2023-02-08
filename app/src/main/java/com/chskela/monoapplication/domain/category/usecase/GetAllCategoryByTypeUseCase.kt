package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.models.TypeCategory
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoryByTypeUseCase (private val categoryRepository: CategoryRepository) {

    operator fun invoke(type: TypeCategory) : Flow<List<Category>> {
        return categoryRepository.getAllCategoryByType(type)
    }
}