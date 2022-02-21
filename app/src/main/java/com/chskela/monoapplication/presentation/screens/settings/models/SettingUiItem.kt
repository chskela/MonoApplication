package com.chskela.monoapplication.presentation.screens.settings.models

import androidx.compose.ui.graphics.Color

data class SettingUiItem(
     val title: String,
     val leftIcon: Int,
     val rightIcon: Int? = null,
     val color: Color,
     val onClick: () -> Unit = {}
)
