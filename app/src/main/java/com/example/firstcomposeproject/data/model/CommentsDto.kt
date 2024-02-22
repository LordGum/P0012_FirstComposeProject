package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class CommentsDto(
    @SerializedName("count") val count: Int
)
