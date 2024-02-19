package com.example.firstcomposeproject.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.presentation.main.comments.CommentViewModel
import com.example.firstcomposeproject.presentation.main.comments.CommentsScreen
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val viewModel2 by viewModels<CommentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeProjectTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(8.dp)
                ) {
                    val comments = viewModel2.testListComments
                    CommentsScreen(feedPost = FeedPost(), comments = comments)
                    //MainScreen(viewModel)
                }
            }
        }
    }
}






