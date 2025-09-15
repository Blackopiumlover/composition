package com.example.composition.square

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SquareViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(SquareState())
    val state: StateFlow<SquareState> = _state.asStateFlow()
}