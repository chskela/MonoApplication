package com.chskela.monoapplication.domain.themeswitcher.repository

import kotlinx.coroutines.flow.Flow

interface ThemeSwitcher {

    fun isDarkTheme(): Flow<Boolean?>

    suspend fun setDarkTheme(value: Boolean)
}