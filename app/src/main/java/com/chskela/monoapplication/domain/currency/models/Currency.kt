package com.chskela.monoapplication.domain.currency.models

data class Currency(
    val id: Long,
    val name: String,
    val letterCode: String,
    val symbol: String,
)
