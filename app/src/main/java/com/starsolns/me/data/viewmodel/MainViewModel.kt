package com.starsolns.me.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.starsolns.me.data.datastore.SessionManager
import com.starsolns.me.data.repository.UserRepository
import com.starsolns.me.model.*
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,
    application: Application
): AndroidViewModel(application) {

    val loginResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    //val registerResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val registerResponse: MutableLiveData<UserResponse> = MutableLiveData()

    val users: MutableLiveData<UsersResponse> = MutableLiveData()
    val userProfile: MutableLiveData<MyProfileResponse> = MutableLiveData()

    val readToken = sessionManager.readAccessToken.asLiveData()
    val isLoggedIn = sessionManager.readIsLoggedIn.asLiveData()

    val loading: MutableLiveData<Boolean> = MutableLiveData()


    suspend fun registerUser(userRegister: UserRegister) {
        try {
            val response = userRepository.registerUser(userRegister)
            registerResponse.value = response

        }catch (e: Exception){

        }
    }

    fun getAllUsers(){
        viewModelScope.launch {
            val response = userRepository.getAllUsers()
            users.value = response
        }
    }

    fun getProfile(){
        viewModelScope.launch {
            loading.value = true
            userProfile.value = userRepository.getProfile()
            loading.value = false
        }
    }


    /** Using Callback method*/

    fun registerUserCall(name: String, email: String, phone: String, password: String){
        userRepository.registerUserCall(name, email, phone, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    val fetchedResponse = response.body()
                    registerResponse.value = fetchedResponse!!
                }
            }

            override fun onFailure(call: Call<UserResponse>, error: Throwable) {
            }

        })
    }

    fun registerUserCall2(userRegister: UserRegister){
        userRepository.registerUserCall2(userRegister).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    val fetchedResponse = response.body()
                    registerResponse.value = fetchedResponse!!
                }
            }

            override fun onFailure(call: Call<UserResponse>, error: Throwable) {
            }

        })
    }

    fun saveToken(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            sessionManager.saveToken(token)
        }
    }

    fun setIsLoggedIn(isLogged: Boolean){
        viewModelScope.launch {
            sessionManager.isLoggedIn(isLogged)
        }
    }


}