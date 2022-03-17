package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.data.transaction.storage.models.TransactionEntityWithCategory
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsUseCase(private val transactionRepository: TransactionRepository) {

    operator fun invoke() : Flow<List<TransactionEntityWithCategory>> = transactionRepository.getAllTransactions()
}

