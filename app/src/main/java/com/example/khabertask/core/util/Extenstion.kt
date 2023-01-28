package com.example.khabertask.core.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.khabertask.R

fun ImageView.loadImage(url:String){

    Glide.with(this.context)
        .load(url)
        .error(R.drawable.account)
        .centerCrop()
        .into(this)
}