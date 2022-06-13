package com.starsolns.me.data.network

import com.starsolns.me.model.UserLogin
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.model.Users
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

    @GET("/api/v1/user")
    fun getUsers(): List<Users>

}