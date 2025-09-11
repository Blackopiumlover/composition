package com.example.composition.home

sealed class HomePageEvent {
    object ExitApp: HomePageEvent()
}