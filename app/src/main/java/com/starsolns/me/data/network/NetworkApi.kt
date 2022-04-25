package com.starsolns.me.data.network

import com.starsolns.me.model.UserLogin
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.util.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkApi {

    @POST("user/login")
    suspend fun loginUser(@Body userLogin: UserLogin): UserResponse

    @POST("user")
    suspend fun registerUser(@Body userRegister: UserRegister): UserResponse



}