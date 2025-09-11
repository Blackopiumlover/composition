package com.example.composition.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _eventChannel = Channel<HomePageEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun processIntent(intent: HomePageIntent) {
        when (intent) {
            is HomePageIntent.ExitApp -> exitApp()
        }
    }

    private fun exitApp() {
        viewModelScope.launch {
            _eventChannel.send(HomePageEvent.ExitApp)
        }
    }
}