package com.starsolns.me.data.repository

import com.starsolns.me.data.network.NetworkApi
import com.starsolns.me.model.*
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val networkApi: NetworkApi
) {

    /** Using Coroutines */

    suspend fun loginUser(userLogin: UserLogin): UserResponse{
        return networkApi.loginUser(userLogin)
    }

    suspend fun registerUser(userRegister: UserRegister): UserResponse{
        return networkApi.registerUser(userRegister)
    }


    suspend fun getAllUsers(): UsersResponse{
        return networkApi.getUsers()
    }

    suspend fun getProfile(): MyProfileResponse{
        return networkApi.getProfile()
    }

    /** Using Callback Method*/

    fun registerUserCall(name: String, email: String, phone: String, password: String): Call<UserResponse>{
        return networkApi.registerUserCall(name, email, phone, password)
    }

    fun registerUserCall2(userRegister: UserRegister): Call<UserResponse>{
        return networkApi.registerUserCall2(userRegister)
    }



}