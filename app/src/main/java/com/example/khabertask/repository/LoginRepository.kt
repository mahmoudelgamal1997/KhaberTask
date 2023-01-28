package com.example.khabertask.repository

import com.example.khabertask.data.ApiInterface
import javax.inject.Inject

class LoginRepository @Inject constructor(val apiInterface: ApiInterface) {
    suspend fun login(phone:String,password:String)= apiInterface.login(phone = phone, password = password)
}