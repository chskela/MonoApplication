package com.chskela.monoapplication.domain.transaction.usercase

data class TransactionUseCases(
    val addTransactionUseCase: AddTransactionUseCase,
    val deleteTransactionUseCase: DeleteTransactionUseCase,
    val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    val updateTransactionUseCase: UpdateTransactionUseCase
)
