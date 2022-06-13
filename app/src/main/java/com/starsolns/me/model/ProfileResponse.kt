package com.starsolns.me.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse (
    @SerializedName("_id")
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val role: String
)