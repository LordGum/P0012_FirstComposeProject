package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class CommentsResponseDto(
    @SerializedName("response") val content: CommentsContentDto
)