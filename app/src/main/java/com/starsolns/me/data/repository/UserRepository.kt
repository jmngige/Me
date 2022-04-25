package com.starsolns.me.data.repository

import com.starsolns.me.data.network.NetworkApi
import com.starsolns.me.model.UserLogin
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun loginUser(userLogin: UserLogin): UserResponse{
        return networkApi.loginUser(userLogin)
    }

    suspend fun registerUser(userRegister: UserRegister): UserResponse{
        return networkApi.registerUser(userRegister)
    }

}