package com.starsolns.me.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse (
    val fullName: String,
    val email: String,
    val phone: String,
    val role: String
)