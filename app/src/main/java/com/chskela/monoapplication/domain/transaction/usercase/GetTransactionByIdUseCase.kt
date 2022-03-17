package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionByIdUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(id: Long) : TransactionEntityWithCategory = transactionRepository.getTransactionById(id)
}

