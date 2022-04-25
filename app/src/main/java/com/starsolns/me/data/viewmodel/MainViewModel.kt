package com.starsolns.me.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starsolns.me.data.repository.UserRepository
import com.starsolns.me.model.UserRegister
import com.starsolns.me.model.UserResponse
import com.starsolns.me.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application
): AndroidViewModel(application) {

    val loginResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    val registerResponse: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()

    fun registerUser(userRegister: UserRegister){
        viewModelScope.launch(Dispatchers.IO){
            getRegistrationSafeCall(userRegister)
        }
    }

    private suspend fun getRegistrationSafeCall(userRegister: UserRegister) {
        registerResponse.value = NetworkResult.Loading()
        try {
            val response = userRepository.registerUser(userRegister)
            registerResponse.value = handleResponse(response)

        }catch (e: Exception){
            registerResponse.value = NetworkResult.Error("Error occurred")
        }
    }

    private fun handleResponse(response: UserResponse): NetworkResult<UserResponse>? {
        return when {
            response.success -> {
                NetworkResult.Success(response)
            }
            else -> {
                NetworkResult.Error("Some error Occurred")
            }
        }
    }


}