package com.starsolns.me.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)