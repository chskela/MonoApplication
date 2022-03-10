package com.chskela.monoapplication.presentation.ui.components.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconActive: Int
)
