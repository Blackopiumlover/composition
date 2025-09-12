package com.example.composition.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composition.R

@Composable
fun HomeTopBar(
    selectedIndex: Int,
    tabs: List<String>,
    onExit: () -> Unit,
    navigateToCollection: () -> Unit,
    navigateToHistory: () -> Unit,
    changePeriod: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        val (exitIcon, tabRow, collectionIcon, historyIcon, changePeriodIcon, periodText) = createRefs()

        Image(
            painter = painterResource(id = R.mipmap.ic_home),
            contentDescription = "Exit",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(exitIcon) {
                    start.linkTo(parent.start, 28.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    onClick = onExit
                )
        )

        HomeTabRow(
            selectedIndex = selectedIndex,
            tabList = tabs,
            modifier = Modifier
                .constrainAs(tabRow) {
                    start.linkTo(exitIcon.end, 346.dp)
                    end.linkTo(collectionIcon.start, 206.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_home_bookmark),
            contentDescription = "Collection",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(collectionIcon) {
                    end.linkTo(historyIcon.start, 28.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    onClick = navigateToCollection
                )
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_home_history),
            contentDescription = "History",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(historyIcon) {
                    end.linkTo(changePeriodIcon.start, 28.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    onClick = navigateToHistory
                )
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_home_switch_stage),
            contentDescription = "Change Period",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(changePeriodIcon) {
                    end.linkTo(periodText.start, 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    onClick = changePeriod
                )
        )

        Text(
            text = "小学",
            fontSize = 18.sp,
            lineHeight = 24.sp,
            color = Color(0xff6564ab),
            modifier = Modifier
                .constrainAs(periodText) {
                    end.linkTo(parent.end, 28.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Preview
@Composable
fun PreviewHomeTopBar() {
    HomeTopBar(
        selectedIndex = 0,
        tabs = listOf("首页", "练习", "范文", "广场"),
        onExit = {},
        navigateToCollection = {},
        navigateToHistory = {},
        changePeriod = {}
    )
}