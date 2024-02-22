package com.example.firstcomposeproject.presentation.main.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstcomposeproject.R
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType
import com.example.firstcomposeproject.ui.theme.DarkRed

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onCommentClickListener: (StatisticType) -> Unit,
    onLikeClickListener: (StatisticType) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            PostTop(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = feedPost.contentImageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            PostBottom(
                feedPost,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener,
                isFavourite = feedPost.isLiked
            )
        }
    }
}

@Composable
fun PostAvatar(feedPost: FeedPost) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }

    }
}

@Composable
fun PostTop(feedPost: FeedPost) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        PostAvatar(feedPost)
        Icon (
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PostBottom(
    feedPost: FeedPost,
    onCommentClickListener: (StatisticType) -> Unit,
    onLikeClickListener: (StatisticType) -> Unit,
    isFavourite: Boolean
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row {
            Row(
                modifier = Modifier.weight(4f)
            ) {
                IconWithText(feedPost,
                    StatisticType.VIEWS,
                    R.drawable.ic_views_count,
                )
            }
            Row(
                modifier = Modifier.weight(5f),
                Arrangement.SpaceEvenly
            ) {
                IconWithText(feedPost, StatisticType.SHARES, R.drawable.ic_share)
                IconWithText(feedPost, StatisticType.COMMENTS, R.drawable.ic_comment,
                    onItemClickListener = {
                        onCommentClickListener(StatisticType.COMMENTS)
                    }
                )
                IconWithText(
                    feedPost = feedPost,
                    type = StatisticType.LIKES,
                    iconResId = if (isFavourite) R.drawable.ic_like_set else R.drawable.ic_like,
                    onItemClickListener = {
                        onLikeClickListener(StatisticType.LIKES)
                    },
                    tint = if (isFavourite) DarkRed else MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", (count / 1000))
    } else if (count > 1000) {
        String.format("%.1fK", (count / 1000f))
    } else {
        count.toString()
    }
}



@Composable
fun IconWithText(
    feedPost: FeedPost,
    type: StatisticType,
    iconResId: Int,
    onItemClickListener: (() -> Unit)? = null,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    val modifier = if (onItemClickListener == null) {
        Modifier
    } else {
        Modifier.clickable {
            onItemClickListener()
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint,
        )
        Spacer(modifier = Modifier.width(5.dp))
        val count = feedPost.statistics[type] ?: 0
        Text(
            text = formatStatisticCount(count),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
