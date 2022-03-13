package com.chskela.monoapplication.navigation

import androidx.navigation.NamedNavArgument
import com.chskela.monoapplication.presentation.MonoScreens

object MainDirections {

    val Default = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }

    val root = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.Root.name
    }

    val onBoarding = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()
        override val destination = MonoScreens.OnBoarding.name
    }
 }