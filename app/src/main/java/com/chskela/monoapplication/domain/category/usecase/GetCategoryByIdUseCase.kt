package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.repository.CategoryRepository

class GetCategoryByIdUseCase (private val categoryRepository: CategoryRepository) {

     suspend operator fun invoke(id: Long) : Category {
        return categoryRepository.getCategoryById(id)
    }
}