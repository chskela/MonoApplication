package com.chskela.monoapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val letterCode: String,
    val symbol: String
)
