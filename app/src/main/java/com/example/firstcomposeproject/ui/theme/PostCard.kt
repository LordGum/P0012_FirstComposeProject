package com.example.firstcomposeproject.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeproject.R
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType

@Composable
fun PostCard(
    feedPost: FeedPost,
    onViewClickListener: (StatisticType) -> Unit,
    onShareClickListener: (StatisticType) -> Unit,
    onCommentClickListener: (StatisticType) -> Unit,
    onLikeClickListener: (StatisticType) -> Unit
) {
    Log.d("TAG", "recomposition")
    Column {
        PostTop(feedPost)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = feedPost.contentText,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = feedPost.contentImageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(8.dp))
        PostBottom(
            feedPost,
            onViewClickListener = onViewClickListener,
            onShareClickListener = onShareClickListener,
            onCommentClickListener = onCommentClickListener,
            onLikeClickListener = onLikeClickListener
        )
    }
}

@Composable
fun PostAvatar(feedPost: FeedPost) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(5.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
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
    onViewClickListener: (StatisticType) -> Unit,
    onShareClickListener: (StatisticType) -> Unit,
    onCommentClickListener: (StatisticType) -> Unit,
    onLikeClickListener: (StatisticType) -> Unit
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
        ) {
            Row(
                modifier = Modifier.weight(4f)
            ) {
                IconWithText(feedPost,
                    StatisticType.VIEWS,
                    R.drawable.ic_views_count,
                    onItemClickListener = {
                        onViewClickListener(StatisticType.VIEWS)
                    }
                )
            }
            Row(
                modifier = Modifier.weight(5f),
                Arrangement.SpaceEvenly
            ) {
                IconWithText(feedPost, StatisticType.SHARES, R.drawable.ic_share,
                    onItemClickListener = {
                        onShareClickListener(StatisticType.SHARES)
                    }
                )
                IconWithText(feedPost, StatisticType.COMMENTS, R.drawable.ic_comment,
                    onItemClickListener = {
                        onCommentClickListener(StatisticType.COMMENTS)
                    }
                )
                IconWithText(feedPost, StatisticType.LIKES, R.drawable.ic_like,
                    onItemClickListener = {
                        onLikeClickListener(StatisticType.LIKES)
                    }
                )
            }
        }
    }
}

@Composable
fun IconWithText(
    feedPost: FeedPost,
    type: StatisticType,
    iconResId: Int,
    onItemClickListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                onItemClickListener()
            }
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = Modifier.width(5.dp))
        val count = feedPost.statistics[type]
        Text(
            text = count.toString(),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
