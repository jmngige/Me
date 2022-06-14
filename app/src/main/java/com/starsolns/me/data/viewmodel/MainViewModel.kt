package com.starsolns.me.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starsolns.me.data.repository.UserRepository
import com.starsolns.me.model.*
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application
): AndroidViewModel(application) {

    val loginResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    //val registerResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val registerResponse: MutableLiveData<UserResponse> = MutableLiveData()

    val users: MutableLiveData<UsersResponse> = MutableLiveData()
    val userProfile: MutableLiveData<MyProfileResponse> = MutableLiveData()

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


}