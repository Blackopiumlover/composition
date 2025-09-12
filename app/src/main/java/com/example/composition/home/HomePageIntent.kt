package com.example.composition.home

sealed class HomePageIntent {
    object ExitApp: HomePageIntent()

    object ClickCollection: HomePageIntent()

    object ClickHistory: HomePageIntent()

    data class ChangePeriod(val newPeriodCode: String): HomePageIntent()

    data class SelectTab(val index: Int): HomePageIntent()
}