package com.example.khabertask.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabertask.data.models.DetailsResponse
import com.example.khabertask.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repository: DetailsRepository) : ViewModel() {


    val stateFlow: MutableStateFlow<DetailsResponse?> = MutableStateFlow(DetailsResponse())
    suspend fun getDetails(token: String) {
        viewModelScope.launch {
            val respsponse = repository.getDetails(token)
            if (respsponse.isSuccessful) {
                withContext(Dispatchers.Main) {
                    stateFlow.value = respsponse.body()
                }
            } else {
                Log.e("ggggg",respsponse.message())
                stateFlow.value = null
            }
        }
    }
}