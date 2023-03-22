package com.chskela.monoapplication.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.StateFlow

@Composable
fun isAppDarkTheme(darkTheme: StateFlow<Boolean?>): Boolean {
    val theme by darkTheme.collectAsState(initial = null)

    return when (theme) {
        false -> false
        true -> true
        null -> isSystemInDarkTheme()
    }
}