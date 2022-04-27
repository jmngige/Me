package com.starsolns.me.model


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String

)