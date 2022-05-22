package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryByIdUseCase (private val categoryRepository: CategoryRepository) {

     operator fun invoke(id: Long) : Flow<Category> {
        return categoryRepository.getCategoryById(id)
    }
}