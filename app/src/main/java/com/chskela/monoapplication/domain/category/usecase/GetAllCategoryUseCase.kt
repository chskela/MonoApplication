package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoryUseCase (private val categoryRepository: CategoryRepository) {

    operator fun invoke() : Flow<List<Category>> {
        return categoryRepository.getAllCategory()
    }
}