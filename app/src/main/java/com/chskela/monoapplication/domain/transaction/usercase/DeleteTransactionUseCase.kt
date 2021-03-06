package com.chskela.monoapplication.domain.transaction.usercase

import com.chskela.monoapplication.domain.transaction.models.Transaction
import com.chskela.monoapplication.domain.transaction.repository.TransactionRepository

class DeleteTransactionUseCase(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(transaction: Transaction) {
        transactionRepository.deleteTransaction(transaction)
    }
}

