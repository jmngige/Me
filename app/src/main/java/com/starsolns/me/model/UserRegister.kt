package com.starsolns.me.model


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String
)