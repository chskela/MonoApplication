package com.chskela.monoapplication.domain.category.usecase

data class CategoryUseCases(
    val getAllCategoryUseCase: GetAllCategoryUseCase,
    val addCategoryUseCase: AddCategoryUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase,
    val updateCategoryUseCase: UpdateCategoryUseCase,
    val getCategoryByIdUseCase: GetCategoryByIdUseCase
)
