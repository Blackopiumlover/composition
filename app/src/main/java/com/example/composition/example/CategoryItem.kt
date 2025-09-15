package com.example.composition.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.R
import com.example.composition.data.Category
import com.example.composition.data.textbookList

@Composable
fun CategoryItem(
    isExpanded: Boolean,
    category: Category,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier
                .width(272.dp)
        ) {
            Image(
                painter = painterResource(id = categoryIconResourceId(category.categoryName)),
                contentDescription = "Category Icon",
                modifier = Modifier.size(24.dp)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = category.categoryName,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = Color(0xFF333333),
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = if (isExpanded) R.mipmap.bc_example_essay_category_close else R.mipmap.bc_example_essay_category_open),
                contentDescription = "Expand / Collapse",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(                                        
            modifier = Modifier.height(12.dp)          
        )

        if (isExpanded) {
            category.topicList.forEachIndexed { index, topic ->
                TopicItem(
                    isSelected = true,
                    topic = topic
                )

                if (index < category.topicList.lastIndex) {
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
            }
        }
    }
}

private fun categoryIconResourceId(categoryName: String): Int {
    return categoryIconMap[categoryName] ?: R.mipmap.bc_example_essay_category_icon_self
}

private val categoryIconMap = mapOf(
    "人与自我" to R.mipmap.bc_example_essay_category_icon_self,
    "人与自然" to R.mipmap.bc_example_essay_category_icon_nature_science,
    "人与社会" to R.mipmap.bc_example_essay_category_icon_society_culture,
    "自我探索" to R.mipmap.bc_example_essay_category_icon_self,
    "自然科学" to R.mipmap.bc_example_essay_category_icon_nature_science,
    "社会文化" to R.mipmap.bc_example_essay_category_icon_society_culture,
    "邮件写作" to R.mipmap.bc_example_essay_category_icon_email_writing,
    "看图写作" to R.mipmap.bc_example_essay_category_icon_picture_writing,
    "文章写作" to R.mipmap.bc_example_essay_category_icon_article_writing
)

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem(
        isExpanded = true,
        category = textbookList[0].categoryList[0]
    )
}