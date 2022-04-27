package com.starsolns.me.data.network

import com.starsolns.me.model.UserLogin
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.util.NetworkResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkApi {

    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body userLogin: UserLogin): UserResponse

    @POST("/api/v1/user")
    suspend fun registerUser(@Body userRegister: UserRegister): UserResponse

    @POST("/api/v1/user")
    @FormUrlEncoded
    fun registerUserCall(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<UserResponse>

    @POST("/api/v1/user")
    fun registerUserCall2(
        @Body() userRegister: UserRegister
    ): Call<UserResponse>



}