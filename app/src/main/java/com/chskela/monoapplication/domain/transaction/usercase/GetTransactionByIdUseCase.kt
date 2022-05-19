package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.data.reports.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository

class GetTransactionByIdUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(id: Long): TransactionEntityWithCategory =
        transactionRepository.getTransactionById(id)
}

