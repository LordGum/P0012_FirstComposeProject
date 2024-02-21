package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class LikeDto (
    @SerializedName("count") val count: Int,
    @SerializedName("user_likes") val isUserLikedPost: Byte
)