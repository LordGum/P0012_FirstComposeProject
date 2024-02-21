package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("count") val count: Int
)
