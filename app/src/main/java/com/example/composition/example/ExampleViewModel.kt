package com.example.composition.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(ExampleState())
    val state: StateFlow<ExampleState> = _state.asStateFlow()

    fun processIntent(intent: ExampleIntent) {
        when (intent) {
            is ExampleIntent.SelectTextbook -> handleSelectTextbook(intent.index)
            is ExampleIntent.ToggleCategory -> handleToggleCategory(intent.index)
            is ExampleIntent.SelectTopic -> handleSelectTopic(intent.index)
        }
    }

    private fun handleSelectTextbook(index: Int) {
        if (index == _state.value.selectedTextbookIndex) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedTextbookIndex = index,
                    expandedCategoryIndex = 0,
                    selectedTopicIndex = 0
                )
            }
        }
    }

    private fun handleToggleCategory(index: Int) {
        if (index == _state.value.expandedCategoryIndex) {
            _state.update {
                it.copy(
                    isSelectedCategoryExpanded = !_state.value.isSelectedCategoryExpanded
                )
            }
            return
        } else {
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isSelectedCategoryExpanded = false,
                        expandedCategoryIndex = index,
                        selectedTopicIndex = 0
                    )
                }
            }
        }
    }

    private fun handleSelectTopic(index: Int) {
        if (index == _state.value.selectedTopicIndex) return
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedTopicIndex = index
                )
            }
        }
    }
}