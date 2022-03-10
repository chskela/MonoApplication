package com.chskela.monoapplication.presentation

import com.chskela.monoapplication.R
import com.chskela.monoapplication.presentation.ui.components.bottomnavigation.MenuItem

enum class MonoScreens(val menuItem: MenuItem? = null) {
    OnBoarding,
    Transaction(
        MenuItem(
            label = R.string.bottom_navigation_input,
            icon = R.drawable.bottom_navigation_input,
            iconActive = R.drawable.bottom_navigation_input_fill
        )
    ),
    MonthReport(
        MenuItem(
            label = R.string.bottom_navigation_report,
            icon = R.drawable.bottom_navigation_report,
            iconActive = R.drawable.bottom_navigation_report_fill)
    ),
    Setting(
        MenuItem(
        label = R.string.bottom_navigation_settings,
        icon = R.drawable.bottom_navigation_settings,
        iconActive = R.drawable.bottom_navigation_settings_fill));

    companion object {
        fun fromRoute(route: String?): MonoScreens =
            when (route?.substringBefore("/")) {
                OnBoarding.name -> OnBoarding
                Transaction.name -> Transaction
                MonthReport.name -> MonthReport
                Setting.name -> Setting
                null -> Transaction
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}