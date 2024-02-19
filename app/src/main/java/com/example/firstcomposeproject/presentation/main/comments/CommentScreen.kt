package com.example.firstcomposeproject.presentation.main.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.domain.Comment
import com.example.firstcomposeproject.domain.FeedPost

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    comments: List<Comment>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Comments for FeedPost id: 0",
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Button back to Posts",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 72.dp,
                start = 8.dp,
                end = 8.dp
            )
        ) {
            items(
                items = comments,
                key = {it.id}
            ) {item ->
                Comment(item)
            }
        }
    }
}

@Composable
private fun Comment(comment: Comment) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(CircleShape)
                    .size(35.dp),
                painter = ColorPainter(Color.Cyan),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
            ) {
                Text(
                    text = comment.authorName,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = comment.contentText,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = comment.time,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        }


}
