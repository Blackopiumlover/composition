package com.example.composition.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composition.navigation.NavigationHandler
import com.example.composition.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationHandler: NavigationHandler
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun processIntent(intent: HomePageIntent) {
        when (intent) {
            is HomePageIntent.ExitApp -> exitApp()
            is HomePageIntent.ClickCollection -> navigateToCollection()
            is HomePageIntent.ClickHistory -> navigateToHistory()
            is HomePageIntent.ChangePeriod -> changePeriod(intent.newPeriodCode)
            is HomePageIntent.SelectTab -> selectTab(intent.index)
        }
    }

    private fun exitApp() {
        viewModelScope.launch {
            navigationHandler.exitApp()
        }
    }

    private fun navigateToCollection() {
        viewModelScope.launch {
            navigationHandler.navigateTo(Screen.Collection.route)
        }
    }

    private fun navigateToHistory() {
        viewModelScope.launch {
            navigationHandler.navigateTo(Screen.History.route)
        }
    }

    private fun changePeriod(periodCode: String) {

    }

    private fun selectTab(index: Int) {
        if (index == _state.value.currentSelectedTabIndex) return
        _state.update {
            it.copy(
                currentSelectedTabIndex = index
            )
        }
    }
}