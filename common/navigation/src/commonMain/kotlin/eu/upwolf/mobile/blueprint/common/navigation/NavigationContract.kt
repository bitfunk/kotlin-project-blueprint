/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.navigation

import kotlinx.coroutines.channels.Channel

interface NavigationContract {

    interface Navigator {
        fun navigateUp(): Boolean
        fun navigate(route: String)
        val destinations: Channel<NavigatorEvent>
    }

    sealed class NavigatorEvent {
        object NavigateUp : NavigatorEvent()
        class Directions(val destination: String) : NavigatorEvent()
    }

    fun interface Destination {
        fun route(): String
        val arguments: List<String>
            get() = emptyList()

        val deepLinks: List<String>
            get() = emptyList()
    }
}

val dest = NavigationContract.Destination { "route" }
