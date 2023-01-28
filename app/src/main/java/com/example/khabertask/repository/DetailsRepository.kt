package com.example.khabertask.repository

import com.example.khabertask.data.ApiInterface
import javax.inject.Inject

class DetailsRepository @Inject constructor(val apiInterface: ApiInterface){
    suspend fun getDetails(token:String)= apiInterface.getUserData(token)
}