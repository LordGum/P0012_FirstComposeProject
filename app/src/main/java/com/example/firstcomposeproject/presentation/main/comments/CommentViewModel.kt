package com.example.firstcomposeproject.presentation.main.comments

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.Comment

class CommentViewModel: ViewModel() {
    val testListComments = mutableListOf<Comment>().apply {
        repeat(10) {id ->
            add(Comment(id = id))
        }
    }
}