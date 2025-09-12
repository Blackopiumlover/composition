package com.example.composition.navigation

sealed class NavigationEvent {
    data class NavigateTo(val route: String): NavigationEvent()

    object ExitApp: NavigationEvent()

    data class PopBackStack(val route: String): NavigationEvent()
}