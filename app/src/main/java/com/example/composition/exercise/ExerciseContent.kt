package com.example.composition.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composition.R

@Composable
fun ExerciseContent() {
    ConstraintLayout {
        val (
            background,
            correctClickArea,
            ideaClickArea,
            correctText,
            ideaText,
            commonAndTHiNKText,
            threeAText,
            threeAExplainIcon,
            aiIdeaText
        ) = createRefs()

        Box(
            modifier = Modifier
                .size(856.dp, 544.dp)
                .constrainAs(background) {
                    start.linkTo(parent.start, 116.dp)
                    end.linkTo(parent.end, 116.dp)
                    top.linkTo(parent.top, 4.dp)
                }
                .paint(painter = painterResource(R.mipmap.bg_home_correction))
        )

        Box(
            modifier = Modifier
                .size(400.dp, 492.dp)
                .background(color = Color(0x60FF0000))
                .constrainAs(correctClickArea) {
                    start.linkTo(parent.start, 116.dp)
                    top.linkTo(background.top, 16.dp)
                }
        )

        Box(
            modifier = Modifier
                .size(400.dp, 492.dp)
                .background(color = Color(0x600000FF))
                .constrainAs(ideaClickArea) {
                    end.linkTo(parent.end, 116.dp)
                    top.linkTo(background.top, 16.dp)
                }
        )

        Text(
            text = "作文批改",
            fontSize = 26.sp,
            lineHeight = 37.sp,
            color = Color(0xff557fdd),
            modifier = Modifier
                .constrainAs(correctText) {
                    start.linkTo(parent.start, 258.dp)
                    end.linkTo(parent.end, 726.dp)
                    top.linkTo(background.top, 364.dp)
                }
        )

        Text(
            text = "写作思路",
            fontSize = 26.sp,
            lineHeight = 37.sp,
            color = Color(0xff7272ea),
            modifier = Modifier
                .constrainAs(ideaText) {
                    end.linkTo(parent.end, 280.dp)
                    top.linkTo(background.top, 364.dp)
                }
        )

        Text(
            text = "通用 / KET / PET",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color(0xffffffff),
            modifier = Modifier
                .constrainAs(commonAndTHiNKText) {
                    start.linkTo(background.start, 129.dp)
                    top.linkTo(background.top, 434.dp)
                }
        )

        Text(
            text = "3A批改法",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color(0xffffffff),
            modifier = Modifier
                .constrainAs(threeAText) {
                    start.linkTo(background.start, 150.dp)
                    top.linkTo(commonAndTHiNKText.bottom)
                }
        )

        Image(
            painter = painterResource(R.mipmap.bc_home_question_circle),
            contentDescription = "3A Explanation",
            modifier = Modifier
                .size(16.dp)
                .constrainAs(threeAExplainIcon) {
                    start.linkTo(threeAText.end, 4.dp)
                    top.linkTo(threeAText.top)
                    bottom.linkTo(threeAText.bottom)
                }
        )

        Text(
            text = "AI生成写作思路",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color(0xffffffff),
            modifier = Modifier
                .constrainAs(aiIdeaText) {
                    end.linkTo(background.end, 162.dp)
                    top.linkTo(background.top, 434.dp)
                }
        )
    }
}