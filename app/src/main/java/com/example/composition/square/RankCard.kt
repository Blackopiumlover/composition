package com.example.composition.square

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composition.R

@Composable
fun RankCard(
    rank: Int,
    isWeekRank: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.width(528.dp)
            .height(200.dp)
            .paint(
                painter = painterResource(
                    id = if (rank <= 0) {
                        if (isWeekRank) {
                            R.mipmap.bc_square_week_no_rank_card_background
                        } else {
                            R.mipmap.bc_square_month_no_rank_card_background
                        }
                    } else {
                        if (isWeekRank) {
                            R.mipmap.bc_square_week_has_rank_card_background
                        } else {
                            R.mipmap.bc_square_month_has_rank_card_background
                        }
                    }
                )
            )
    ) {
        // rank > 0 说明本周 / 本月文章上榜了，这时候展示有字的背景图，把排名填进空中
        if (rank > 0) {
            ConstraintLayout {
                val (myRank) = createRefs()

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(16.dp)
                        .height(18.dp)
                        .constrainAs(myRank) {
                            start.linkTo(parent.start, 134.dp)
                            top.linkTo(parent.top, 158.dp)
                        }
                ) {
                    Text(
                        text = rank.toString(),
                        style = RubikMediumItalic.copy(
                            fontSize = 14.sp,
                            color = if (isWeekRank) Color(0xFFFE94B2) else Color(0xFFB8A5FF)
                        ),
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }
        }
    }
}

val RubikMediumItalic = TextStyle(
    fontFamily = FontFamily(Font(R.font.rubik_medium_italic, FontWeight.Medium, FontStyle.Italic)),
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Italic
)

@Preview
@Composable
fun PreviewRankCard() {
    Row {
        RankCard(
            rank = 10,
            isWeekRank = true
        )

        Spacer(
            modifier = Modifier.width(100.dp)
        )

        RankCard(
            rank = 10,
            isWeekRank = false
        )
    }
}