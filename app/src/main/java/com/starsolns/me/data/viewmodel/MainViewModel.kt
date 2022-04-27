package com.starsolns.me.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.starsolns.me.data.repository.UserRepository
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
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


    suspend fun registerUser(userRegister: UserRegister) {
        try {
            val response = userRepository.registerUser(userRegister)
            registerResponse.value = response

        }catch (e: Exception){

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