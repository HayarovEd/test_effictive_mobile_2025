package com.edurda77.resources.model
import kotlinx.serialization.Serializable

sealed class NavigationRoute {
    @Serializable
    data object Login : NavigationRoute()

    @Serializable
    data object Main : NavigationRoute()

    @Serializable
    data object Favorite : NavigationRoute()

    @Serializable
    data object Profile : NavigationRoute()

}