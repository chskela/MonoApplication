package com.chskela.monoapplication.navigation

import androidx.navigation.NamedNavArgument
import com.chskela.monoapplication.presentation.MonoScreens

object BottomNavDirections {

    val root = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.BottomNav.name
    }

   val transaction = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.Transaction.name
    }

    val monthReport = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.MonthReport.name
    }

    val setting = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.Setting.name
    }
}