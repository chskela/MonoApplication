package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetAllTransactionsUseCase(private val transactionRepository: TransactionRepository) {

    operator fun invoke() : Flow<List<Transaction>> = transactionRepository.getAllTransactions()
}

