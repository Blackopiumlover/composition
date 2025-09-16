package com.example.composition.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    onCategoryToggled: () -> Unit,
    selectedTopicIndex: Int,
    onTopicSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val topics = category.topicList

    // 缓存资源 ID，避免每次重组时重新计算
    val categoryIcon = remember(category.categoryName) {
        categoryIconResourceId(category.categoryName)
    }

    // 缓存展开 / 折叠图标资源 ID
    val expandCollapseIcon = remember(isExpanded) {
        if (isExpanded) R.mipmap.bc_example_essay_category_close else R.mipmap.bc_example_essay_category_open
    }

    // 预先组合常用修饰符
    val rowModifier = remember {
        Modifier
            .width(272.dp)
            .clickable(
                onClick = onCategoryToggled
            )
    }
    
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = rowModifier
        ) {
            Image(
                painter = painterResource(id = categoryIcon),
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
                painter = painterResource(id = expandCollapseIcon),
                contentDescription = "Expand / Collapse",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(                                        
            modifier = Modifier.height(12.dp)          
        )

        // 优化条件渲染逻辑，只在展开状态下渲染主题列表
        if (isExpanded) {
            val topicContent = remember(topics, selectedTopicIndex) {
                topics.mapIndexed { index, topic ->
                    index to topic
                }
            }

            topicContent.forEach { (index, topic) ->
                TopicItem(
                    isSelected = index == selectedTopicIndex,
                    topic = topic,
                    onTopicSelected = { onTopicSelected(index) }
                )

                if (index < topics.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
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
        category = textbookList[0].categoryList[0],
        onCategoryToggled = {},
        selectedTopicIndex = 0,
        onTopicSelected = {}
    )
}