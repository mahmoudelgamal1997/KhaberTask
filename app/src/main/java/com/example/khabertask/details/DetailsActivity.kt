package com.example.khabertask.details

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.khabertask.R
import com.example.khabertask.core.util.SharedHelper
import com.example.khabertask.core.util.loadImage
import com.example.khabertask.data.models.DetailsResponse
import com.example.khabertask.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import ir.mahozad.android.PieChart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import java.util.*


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

   private val viewModel:DetailsViewModel by viewModels()
    private val binding:ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            SharedHelper.getString(this@DetailsActivity,"TOKEN")?.let {

                viewModel.getDetails(it)
            }
            viewModel.stateFlow.collectLatest {
                if (it == null) return@collectLatest

                withContext(Dispatchers.Main){
                bindUI(it)
            }
            }
        }


    }

    fun bindUI(model:DetailsResponse){

        binding.apply {

            val user =model.Payroll?.Employee?.get(0)
            val allowence = model.Payroll?.Allowences?.get(0)
            val deduction = model.Payroll?.Deduction?.get(0)
            Log.e("ddddd",user?.EMP_DATA_EN.toString())
            profileImage.loadImage("")
            name.text=user?.EMP_DATA_EN
            specialist.text=user?.SEC_NAME_EN?:"0.0"
            salary.text = user?.SAL_VALUE_A.toString()?:"0.0"
            history.text = model.Payroll?.Date.toString()
            pieChart.slices = listOf(
                PieChart.Slice(0.4f, Color.GRAY),
                PieChart.Slice(0.6f, Color.rgb(255, 165, 0)),

            )
            deserveText.text = allowence?.SAL_VALUE.toString()+" "?:""
            MinusText.text = deduction?.SAL_VALUE.toString()+" "?:""
            TotalText.text =user?.SAL_VALUE_A.toString()?:""
            value2.text=allowence?.SAL_VALUE.toString()?:""
            value3.text = model.Payroll?.Allowences?.get(1)?.SAL_VALUE.toString()?:""
            value4.text = deduction?.SAL_VALUE.toString()?:""
        }
    }
    
    fun getDate (timeStamp:Long):String{
        val cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(timeStamp)
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
    return  "$year , $month"
    }
}