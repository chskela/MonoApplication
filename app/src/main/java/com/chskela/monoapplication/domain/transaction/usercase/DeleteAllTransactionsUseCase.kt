package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository

class DeleteAllTransactionsUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke() {
        transactionRepository.deleteAllTransactions()
    }
}

