package com.example.composition.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composition.R
import com.example.composition.data.Essay
import com.example.composition.data.essay
import kotlin.math.roundToInt

@Composable
fun EssayCard(
    essay: Essay
) {
    ConstraintLayout(
        modifier = Modifier
            .size(344.dp, 200.dp)
            .paint(painter = painterResource(id = R.mipmap.bc_example_essay_card_background))
    ) {
        val (title, brief, divider, difficulty, viewIcon, viewCount, collectIcon, collectCount) = createRefs()

        Text(
            text = essay.titleTranslate,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF333333),
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start, 28.dp)
                top.linkTo(parent.top, 28.dp)
            }.widthIn(max = 288.dp)
        )

        Text(
            text = essay.compositionBrief.replace("\n", " "),
            fontSize = 16.sp,
            color = Color(0x80333333),
            lineHeight = 32.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(288.dp)
                .constrainAs(brief) {
                    start.linkTo(parent.start, 28.dp)
                    end.linkTo(parent.end, 28.dp)
                    top.linkTo(title.bottom, 8.dp)
                }
        )

        Box(
            modifier = Modifier
                .size(288.dp, 1.dp)
                .fillMaxWidth()
                .background(color = Color(0x99d4d2e3))
                .constrainAs(divider) {
                    start.linkTo(parent.start, 28.dp)
                    end.linkTo(parent.end, 28.dp)
                    bottom.linkTo(parent.bottom, 63.dp)
                }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(44.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = textBackgroundColorByDifficulty(essay.difficultyText))
                .constrainAs(difficulty) {
                    start.linkTo(parent.start, 28.dp)
                    top.linkTo(divider.bottom, 16.dp)
                }
        ) {
            Text(
                text = essay.difficultyText,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = textColorByDifficulty(essay.difficultyText),
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
            text = essay.viewCount.toHumanReadableString(),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = Color(0xFF84828F),
            modifier = Modifier.constrainAs(viewCount) {
                end.linkTo(collectIcon.start, 16.dp)
                top.linkTo(divider.bottom, 16.dp)
            }
        )

        Image(
            painter = painterResource(id = if (essay.isCollected == 1) R.mipmap.bc_example_essay_card_collected_icon else R.mipmap.bc_example_essay_card_uncollected_icon),
            contentDescription = "收藏 Icon",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(collectIcon) {
                    end.linkTo(collectCount.start, 4.dp)
                    top.linkTo(collectCount.top, 1.dp)
                }
        )

        Text(
            text = essay.collectCount.toHumanReadableString(),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = Color(0xFF84828F),
            modifier = Modifier.constrainAs(collectCount) {
                end.linkTo(parent.end, 28.dp)
                top.linkTo(divider.bottom, 16.dp)
            }
        )
    }
}

private fun textBackgroundColorByDifficulty(difficulty: String) = when(difficulty) {
    "高级" -> Color(0x26FF5920)
    "中级" -> Color(0x261B86E4)
    "初级" -> Color(0x260FAA88)
    else -> Color(0x26FF5920)
}

private fun textColorByDifficulty(difficulty: String): Color = when (difficulty) {
    "高级" -> Color(0xFFEC6B18)
    "中级" -> Color(0xFF2B73B3)
    "初级" -> Color(0xFF17846C)
    else -> Color(0xFF17846C)
}

fun Int.toHumanReadableString(): String {
    val num = this
    val shortFormStr = if (num < 1000) {
        num.toString()
    } else if (num < 10000) {
        val num = (num / 100f).roundToInt()
        val remain = num % 10
        if (remain == 0)
            "${num / 10}k"
        else
            "${num / 10}.${remain}k"
    } else {
        if (num > 90000) {
            "9w+"
        } else {
            val num = (num / 1000f).roundToInt()
            val remain = num % 10
            if(remain == 0){
                "${num / 10}w"
            }else{
                "${num / 10}.${remain}w"
            }
        }
    }
    return shortFormStr
}

@Preview
@Composable
fun PreviewEssayCard() {
    EssayCard(
        essay = essay
    )
}