package com.example.khabertask.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabertask.data.models.LoginResponse
import com.example.khabertask.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repoLLoginRepository: LoginRepository):ViewModel()
{

    val loginStateFlow:MutableStateFlow<LoginResponse?> = MutableStateFlow(LoginResponse())
    fun login(phone:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response= repoLLoginRepository.login(phone,password)

            if (response.isSuccessful){
                withContext(Dispatchers.Main){
                    loginStateFlow.value=response.body()!!

                }

            }else {
                loginStateFlow.value = null
            }
    }
    }
}