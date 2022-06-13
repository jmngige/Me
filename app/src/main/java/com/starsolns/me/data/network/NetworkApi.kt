package com.starsolns.me.data.network

import com.starsolns.me.model.*
import com.starsolns.me.util.NetworkResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface NetworkApi {

    /** Using Coroutines Method*/

    /** Using
     *Coroutines to fetch Data from API
     */
    @POST("/api/v1/user/login")
    suspend fun loginUser(@Body userLogin: UserLogin): UserResponse

    @POST("/api/v1/user")
    suspend fun registerUser(@Body userRegister: UserRegister): UserResponse

    @GET("/api/v1/user")
    suspend fun getUsers(): UsersResponse

    @GET("/api/v1/user/{id}")
    suspend fun getProfile(@Path("id") userId: String): MyProfileResponse


    /** Using Call Back Method*/

    /** Using
     * @Field and @FormUrlEncoded
     *  Using Callback to fetch data from API
     */
    @POST("/api/v1/user")
    @FormUrlEncoded
    fun registerUserCall(
        @Field("fullName") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Call<UserResponse>

    /** Using
     * @Body without  @FormUrlEncoded
     * while using callback to fetch data from API
     */

    @POST("/api/v1/user")
    fun registerUserCall2(
        @Body() userRegister: UserRegister
    ): Call<UserResponse>

}