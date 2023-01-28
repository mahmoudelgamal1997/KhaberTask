package com.example.khabertask.data

import com.example.khabertask.data.models.DetailsResponse
import com.example.khabertask.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
 import retrofit2.http.GET
 import retrofit2.http.Header
 import retrofit2.http.POST

interface ApiInterface {


    @FormUrlEncoded
    @POST("Salamtak/LogIn")
   suspend fun login (
         @Field("MobileNumber") phone:String,
        @Field("Password") password:String,
     ): Response<LoginResponse>

   @GET("Salamtak/GetPayroll")
   suspend fun getUserData(
       @Header("Authorization") token:String,
   ):Response<DetailsResponse>
}