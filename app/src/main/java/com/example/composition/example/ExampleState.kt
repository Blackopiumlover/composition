package com.example.composition.example

import com.example.composition.data.Textbook
import com.example.composition.data.textbookList

data class ExampleState(
    val textbooks: List<Textbook> = textbookList,
    val selectedTextbookIndex: Int = 0,
    val expandedCategoryIndex: Int = 0,
    val isSelectedCategoryExpanded: Boolean = false,
    val selectedTopicIndex: Int = 0
) {
}