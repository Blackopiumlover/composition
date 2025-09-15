package com.example.composition.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composition.R

@Composable
fun ExampleContent(
    viewModel: ExampleViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val textbooks = state.textbooks
    val selectedTextbookIndex = state.selectedTextbookIndex

    val expandedCategoryIndex = state.expandedCategoryIndex
    val isSelectedCategoryExpanded = state.isSelectedCategoryExpanded

    val selectedTopicIndex = state.selectedTopicIndex

    Row(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.mipmap.bc_example_essay_background))
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 28.dp,
                    top = 20.dp
                )
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    TextbookSelection(
                        selectedIndex = 0,
                        textbooks = textbooks,
                        onTextbookSelected = { index ->
                            viewModel.processIntent(ExampleIntent.SelectTextbook(index))
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(272.dp, 1.dp)
                            .background(color = Color(0x66d4d2e3))
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }
            }

            itemsIndexed(textbooks[selectedTextbookIndex].categoryList) { index, category ->
                CategoryItem(
                    isExpanded = index == expandedCategoryIndex && !isSelectedCategoryExpanded,
                    category = category,
                    onCategoryToggled = {
                        viewModel.processIntent(ExampleIntent.ToggleCategory(index))
                    },
                    selectedTopicIndex = selectedTopicIndex,
                    onTopicSelected = { index ->
                        viewModel.processIntent(ExampleIntent.SelectTopic(index))
                    }
                )

                if (index < textbooks[0].categoryList.lastIndex) {
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewExampleContent() {
    ExampleContent()
}