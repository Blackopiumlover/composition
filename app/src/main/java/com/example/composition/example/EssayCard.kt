package com.example.composition.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composition.R
import com.example.composition.data.Essay
import com.example.composition.data.essay
import kotlin.math.roundToInt

// 预定义常用颜色，避免重复创建
private val dividerColor = Color(0x99d4d2e3)
private val titleColor = Color(0xFF333333)
private val briefColor = Color(0x80333333)
private val countColor = Color(0xFF84828F)

// 预定义难度级别对应的颜色映射
private val difficultyBackgroundColors = mapOf(
    "高级" to Color(0x26FF5920),
    "中级" to Color(0x261B86E4),
    "初级" to Color(0x260FAA88)
)

private val difficultyTextColors = mapOf(
    "高级" to Color(0xFFEC6B18),
    "中级" to Color(0xFF2B73B3),
    "初级" to Color(0xFF17846C)
)

@Composable
fun EssayCard(
    essay: Essay
) {
    // 缓存处理过的文本内容
    val briefText = remember(essay.compositionBrief) {
        essay.compositionBrief.replace("\n", " ")
    }
    
    // 缓存人类可读的数字字符串
    val viewCountText = remember(essay.viewCount) {
        essay.viewCount.toHumanReadableString()
    }
    
    val collectCountText = remember(essay.collectCount) {
        essay.collectCount.toHumanReadableString()
    }
    
    // 缓存难度相关的颜色
    val difficultyBgColor = remember(essay.difficultyText) {
        difficultyBackgroundColors[essay.difficultyText] ?: difficultyBackgroundColors["高级"]!!
    }
    
    val difficultyTextColor = remember(essay.difficultyText) {
        difficultyTextColors[essay.difficultyText] ?: difficultyTextColors["高级"]!!
    }
    
    // 缓存收藏图标资源ID
    val collectIconRes = remember(essay.isCollected) {
        if (essay.isCollected == 1) 
            R.mipmap.bc_example_essay_card_collected_icon 
        else 
            R.mipmap.bc_example_essay_card_uncollected_icon
    }

    ConstraintLayout(
        modifier = Modifier
            .size(344.dp, 200.dp)
            .paint(
                painter = painterResource(id = R.mipmap.bc_example_essay_card_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        val (title, brief, divider, difficulty, viewIcon, viewCount, collectIcon, collectCount) = createRefs()

        // 优化约束，使用 createHorizontalChain 减少布局计算
        val horizontalGuideline = createGuidelineFromStart(28.dp)
        val endGuideline = createGuidelineFromEnd(28.dp)

        Text(
            text = essay.titleTranslate,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = titleColor,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(horizontalGuideline)
                top.linkTo(parent.top, 28.dp)
                end.linkTo(endGuideline)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = briefText,
            fontSize = 16.sp,
            color = briefColor,
            lineHeight = 32.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(brief) {
                start.linkTo(horizontalGuideline)
                end.linkTo(endGuideline)
                top.linkTo(title.bottom, 8.dp)
                width = Dimension.fillToConstraints
            }
        )

        Box(
            modifier = Modifier
                .height(1.dp)
                .background(color = dividerColor)
                .constrainAs(divider) {
                    start.linkTo(horizontalGuideline)
                    end.linkTo(endGuideline)
                    bottom.linkTo(parent.bottom, 63.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(44.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = difficultyBgColor)
                .constrainAs(difficulty) {
                    start.linkTo(horizontalGuideline)
                    top.linkTo(divider.bottom, 16.dp)
                }
        ) {
            Text(
                text = essay.difficultyText,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = difficultyTextColor,
                textAlign = TextAlign.Center
            )
        }

        Image(
            painter = painterResource(id = R.mipmap.bc_example_essay_card_view_icon),
            contentDescription = "浏览 Icon",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(viewIcon) {
                    end.linkTo(viewCount.start, 4.dp)
                    top.linkTo(viewCount.top, 1.dp)
                }
        )

        Text(
            text = viewCountText,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = countColor,
            modifier = Modifier.constrainAs(viewCount) {
                end.linkTo(collectIcon.start, 16.dp)
                top.linkTo(divider.bottom, 16.dp)
            }
        )

        Image(
            painter = painterResource(id = collectIconRes),
            contentDescription = "收藏 Icon",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(collectIcon) {
                    end.linkTo(collectCount.start, 4.dp)
                    top.linkTo(collectCount.top, 1.dp)
                }
        )

        Text(
            text = collectCountText,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = countColor,
            modifier = Modifier.constrainAs(collectCount) {
                end.linkTo(endGuideline)
                top.linkTo(divider.bottom, 16.dp)
            }
        )
    }
}

// 优化数字转换函数，使用更高效的实现
fun Int.toHumanReadableString(): String {
    return when {
        this < 1000 -> this.toString()
        this < 10000 -> {
            val num = (this / 100f).roundToInt()
            if (num % 10 == 0) "${num / 10}k" else "${num / 10}.${num % 10}k"
        }
        this > 90000 -> "9w+"
        else -> {
            val num = (this / 1000f).roundToInt()
            if (num % 10 == 0) "${num / 10}w" else "${num / 10}.${num % 10}w"
        }
    }
}

@Preview
@Composable
fun PreviewEssayCard() {
    EssayCard(
        essay = essay
    )
}