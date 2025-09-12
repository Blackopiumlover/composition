package com.example.composition.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class NavigationHandler @Inject constructor() {
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    suspend fun navigateTo(route: String) {
        _navigationEvents.emit(NavigationEvent.NavigateTo(route))
    }

    suspend fun navigateBack(route: String) {
        _navigationEvents.emit(NavigationEvent.PopBackStack(route))
    }

    suspend fun exitApp() {
        _navigationEvents.emit(NavigationEvent.ExitApp)
    }
}