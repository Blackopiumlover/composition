package com.example.composition.example

sealed class ExampleIntent {
    data class SelectTextbook(val index: Int): ExampleIntent()

    data class ToggleCategory(val index: Int): ExampleIntent()

    data class SelectTopic(val index: Int): ExampleIntent()
}