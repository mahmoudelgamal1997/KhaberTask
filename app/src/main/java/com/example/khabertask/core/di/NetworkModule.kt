package com.example.khabertask.core.di

import com.example.khabertask.data.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.wasabeef.glide.transformations.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val interceptor: HttpLoggingInterceptor by lazy {
         if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }


    val httpClient: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(false)
            .addInterceptor(interceptor)
            .followSslRedirects(false)
            .writeTimeout(60, TimeUnit.SECONDS)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getAPI(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)



}