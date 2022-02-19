package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.models.Category
import com.chskela.monoapplication.domain.category.repository.CategoryRepository

class UpdateCategoryUseCase (private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(category: Category) {
        categoryRepository.updateCategory(category = category)
    }
}