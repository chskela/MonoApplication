package com.chskela.monoapplication.navigation

import com.chskela.monoapplication.navigation.MainDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var commands = MutableStateFlow(Default)

    fun navigate(directions: NavigationCommand) {
        commands.value = directions
    }

}