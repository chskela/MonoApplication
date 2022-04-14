package com.chskela.monoapplication.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.chskela.monoapplication.R

sealed class BottomMenuScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconActive: Int,
) {

    object Transaction : BottomMenuScreens(
        route = MonoScreens.Transaction.name,
        label = R.string.bottom_navigation_input,
        icon = R.drawable.bottom_navigation_input,
        iconActive = R.drawable.bottom_navigation_input_fill,
    )

    object MonthReport : BottomMenuScreens(
        route = MonoScreens.MonthReport.name,
        label = R.string.bottom_navigation_report,
        icon = R.drawable.bottom_navigation_report,
        iconActive = R.drawable.bottom_navigation_report_fill,
    )
    object Setting : BottomMenuScreens(
        route = MonoScreens.SettingRoot.name,
        label = R.string.bottom_navigation_settings,
        icon = R.drawable.bottom_navigation_settings,
        iconActive = R.drawable.bottom_navigation_settings_fill,
    )

}