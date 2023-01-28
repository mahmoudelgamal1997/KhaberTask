package com.example.khabertask.login

import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.khabertask.R
import com.example.khabertask.core.util.SharedHelper
import com.example.khabertask.core.util.SharedHelper.Companion.TOKEN
import com.example.khabertask.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!SharedHelper.getString(this, TOKEN).isNullOrEmpty()){
            Intent(this@LoginActivity,DetailsActivity::class.java).also {
                 startActivity(it)
            }
        }else {
        lifecycleScope.launchWhenCreated {
            login("01068962997","123456")
            listner()
        }
    }}


    fun listner(){
        lifecycleScope.launchWhenCreated {
            loginViewModel.loginStateFlow.collectLatest{reposnse->
                if (reposnse==null) return@collectLatest


                 Intent(this@LoginActivity,DetailsActivity::class.java).also {

                    reposnse.Token?.let { it1 ->
                        SharedHelper.saveString(this@LoginActivity,TOKEN, "Bearer $it1")
                    }
                    startActivity(it)
                }
            }
        }
    }
     fun login(phone:String,password:String)= loginViewModel.login(phone, password)


}