package com.chskela.monoapplication.domain.category.usecase

import com.chskela.monoapplication.domain.category.repository.CategoryRepository

class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(id: Long) {
        categoryRepository.deleteCategory(id)
    }
}