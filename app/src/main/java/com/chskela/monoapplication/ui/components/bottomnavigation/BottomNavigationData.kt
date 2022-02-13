package com.chskela.monoapplication.ui.components.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.chskela.monoapplication.R

object BottomNavigationData {
    @Composable
    fun getData() = listOf(
        MenuItem(label = stringResource(R.string.bottom_navigation_input),
            icon = ImageVector.vectorResource(R.drawable.bottom_navigation_input),
            iconActive = ImageVector.vectorResource(R.drawable.bottom_navigation_input_fill)),

        MenuItem(label = stringResource(R.string.bottom_navigation_calculator),
            icon = ImageVector.vectorResource(R.drawable.bottom_navigation_calculator),
            iconActive = ImageVector.vectorResource(R.drawable.bottom_navigation_calculator_fill)),

        MenuItem(label = stringResource(R.string.bottom_navigation_report),
            icon = ImageVector.vectorResource(R.drawable.bottom_navigation_report),
            iconActive = ImageVector.vectorResource(R.drawable.bottom_navigation_report_fill)),

        MenuItem(label = stringResource(R.string.bottom_navigation_settings),
            icon = ImageVector.vectorResource(R.drawable.bottom_navigation_settings),
            iconActive = ImageVector.vectorResource(R.drawable.bottom_navigation_settings_fill)),
    )
}

