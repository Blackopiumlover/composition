package com.example.composition.square

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Size
import com.example.composition.R
import com.example.composition.data.EssayItem
import com.example.composition.example.toHumanReadableString

@Composable
fun SquareEssayCard(
    essay: EssayItem
) {
    val topicList = essay.topicList

    val topicString = topicList.joinToString(" | ") { it }

    val imageRes = when (essay.level) {
        "A+" -> R.mipmap.ic_a_plus_rated
        "A" -> R.mipmap.ic_a_rated
        "B" -> R.mipmap.ic_b_rated
        "C" -> R.mipmap.ic_c_rated
        "D" -> R.mipmap.ic_d_rated
        "WTG" -> R.mipmap.ic_failed_rated
        "TG" -> R.mipmap.ic_pass_rated
        "YX" -> R.mipmap.ic_excellent_rated
        "ZY" -> R.mipmap.ic_extraordinary_rated
        else -> R.mipmap.ic_a_plus_rated
    }

    ConstraintLayout(
        modifier = Modifier
            .paint(painter = painterResource(id = R.mipmap.bg_collection_card))
            .size(width = 352.dp, height = 208.dp)
    ) {
        val (
            categoryLabel,
            topicLabels,
            level,
            title,
            content,
            divider,
            avatar,
            author,
            date,
            likeIcon,
            likeNum,
            collectionIcon,
            collectionNum
        ) = createRefs()

        Box(
            modifier = Modifier
                .height(24.dp)
                .background(Color(0x268081ff), RoundedCornerShape(4.dp))
                .constrainAs(categoryLabel) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(parent.top, margin = 32.dp)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = essay.categoryName,
                color = Color(0xff7273eb),
                fontSize = 14.sp,
                lineHeight = 19.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .height(24.dp)
                .background(Color(0x26efa131), RoundedCornerShape(4.dp))
                .constrainAs(topicLabels) {
                    start.linkTo(categoryLabel.end, margin = 8.dp)
                    top.linkTo(parent.top, margin = 32.dp)
                }.widthIn(max = 140.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = topicString,
                color = Color(0xffc78b42),
                fontSize = 14.sp,
                lineHeight = 19.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(width = 56.dp, height = 40.dp)
                .constrainAs(level) {
                    end.linkTo(parent.end, margin = 5.dp)
                    top.linkTo(parent.top, margin = 25.dp)
                }
        )

        Text(
            text = essay.compositionTitle,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 32.sp,
            maxLines = 1,
            modifier = Modifier
                .size(width = 288.dp, height = 32.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(parent.top, margin = 64.dp)
                }
        )

        Text(
            text = essay.compositionBrief,
            fontSize = 16.sp,
            color = Color(0x80333333),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 32.sp,
            maxLines = 1,
            modifier = Modifier
                .width(288.dp)
                .constrainAs(content) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(title.bottom, margin = 0.dp)
                }
        )

        Box(
            modifier = Modifier
                .size(288.dp, 1.dp)
                .background(Color(0x66D4D2E3))
                .constrainAs(divider) {
                    top.linkTo(parent.top, margin = 140.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(essay.authorAvatar)
                .size(Size(36, 36))
                .precision(Precision.INEXACT)
                .placeholder(R.mipmap.ic_default_avatar)
                .error(R.mipmap.ic_default_avatar)
                .build(),
            contentDescription = "头像",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .constrainAs(avatar) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(divider.bottom, margin = 10.dp)
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = essay.authorName,
            fontSize = 14.sp,
            color = Color(0xFF84828F),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp,
            maxLines = 1,
            modifier = Modifier
                .width(102.dp)
                .constrainAs(author) {
                    start.linkTo(avatar.end, margin = 8.dp)
                    top.linkTo(divider.bottom, margin = 7.dp)
                }
        )

        Text(
            text = essay.effectiveTime,
            fontSize = 12.sp,
            color = Color(0x8084828F),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 20.sp,
            maxLines = 1,
            modifier = Modifier
                .widthIn(max = 120.dp)
                .constrainAs(date) {
                    start.linkTo(avatar.end, margin = 8.dp)
                    top.linkTo(divider.bottom, margin = 29.dp)
                }
        )

        Image(
            painter = painterResource(if (essay.liked) R.mipmap.bc_icon_liked else R.mipmap.bc_icon_unlike),
            contentDescription = "点赞图标",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(likeIcon) {
                    end.linkTo(likeNum.start, margin = 4.dp)
                    top.linkTo(divider.bottom, margin = 17.dp)
                }
        )

        Text(
            text = essay.likeCount.toHumanReadableString(),
            fontSize = 14.sp,
            color = Color(0xB384828F),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.sp,
            maxLines = 1,
            modifier = Modifier
                .widthIn(max = 30.dp)
                .constrainAs(likeNum) {
                    end.linkTo(collectionIcon.start, margin = 16.dp)
                    top.linkTo(likeIcon.top)
                    bottom.linkTo(likeIcon.bottom)
                }
        )

        Image(
            painter = painterResource(if (essay.collected) R.mipmap.bc_icon_collected else R.mipmap.bc_icon_uncollect),
            contentDescription = "收藏图标",
            modifier = Modifier
                .size(20.dp)
                .constrainAs(collectionIcon) {
                    end.linkTo(collectionNum.start, margin = 4.dp)
                    top.linkTo(divider.bottom, margin = 17.dp)
                }
        )

        Text(
            text = essay.collectCount.toHumanReadableString(),
            fontSize = 14.sp,
            color = Color(0xB384828F),
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.sp,
            maxLines = 1,
            modifier = Modifier
                .widthIn(max = 30.dp)
                .constrainAs(collectionNum) {
                    end.linkTo(parent.end, 32.dp)
                    top.linkTo(collectionIcon.top)
                    bottom.linkTo(collectionIcon.bottom)
                }
        )
    }
}