package com.example.composition.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composition.R
import com.example.composition.data.Textbook
import com.example.composition.data.textbookList

@Composable
fun ExampleContent(
    textbooks: List<Textbook> = textbookList
) {
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
                Column {
                    TextbookSelection(
                        selectedIndex = 0,
                        textbooks = textbooks
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(272.dp, 1.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }
            }

            items(textbooks[0].categoryList) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewExampleContent() {
    ExampleContent(
        textbooks = textbookList
    )
}