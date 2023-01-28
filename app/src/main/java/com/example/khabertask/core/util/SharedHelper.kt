package com.example.khabertask.core.util

import android.content.Context
import android.content.SharedPreferences

class SharedHelper {


    companion object {
        var NAME:String="NAME"
        var JOB:String="JOB"
        var AGE:String= "AGE"
        var GENDER:String= "GENDER"
        var TOKEN:String="TOKEN"
        var sharedPreferences: SharedPreferences? = null

        private fun getSharedPref(context: Context): SharedPreferences? {
            if (sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
            }
            return sharedPreferences
        }

        private fun getSharedPrefEditor(context: Context): SharedPreferences.Editor? {
            return getSharedPref(context)?.edit()
        }

        public fun saveString(context: Context, key: String, value: String) {
            getSharedPrefEditor(context)?.putString(key, value)?.apply()
        }

        public fun saveInt(context: Context, key: String, value: Int) {
            getSharedPrefEditor(context)?.putInt(key, value)?.apply()
        }

        public fun saveBoolean(context: Context, key: String, value: Boolean) {
            getSharedPrefEditor(context)?.putBoolean(key, value)?.apply()
        }

        public fun getString(context: Context, key: String): String? {
            return getSharedPref(context)?.getString(key, "")
        }

        public fun getBoolean(context: Context, key: String): Boolean? {
            return getSharedPref(context)?.getBoolean(key, false)
        }

        public fun getInt(context: Context, key: String): Int? {
            return getSharedPref(context)?.getInt(key, -1)
        }
        public fun removeValue(context: Context, key: String) {
            getSharedPref(context)?.edit()?.remove(key).apply {
                this?.apply()
            }
        }
    }
}


fun Context?.saveInt(key:String,value:Int){
    this?.let { SharedHelper.saveInt(context = it,key,value) }
}

fun Context?.saveString(key:String,value:String){
    this?.let { SharedHelper.saveString(context = it,key,value) }
}

fun Context?.getString(key:String)=this?.let { SharedHelper.getString(context = it,key) }?:""
fun Context?.getInt(key:String)=this?.let { SharedHelper.getInt(context = it,key) }?:-1

