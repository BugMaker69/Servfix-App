package com.example.graduationproject.presentation.on_boarding

import androidx.compose.runtime.mutableStateOf


import androidx.lifecycle.ViewModel
import com.example.graduationproject.R

class OnBoardingViewModel() : ViewModel() {
    val animations = listOf(
        R.raw.worker, R.raw.workers2, R.raw.location
    )
    val descriptin = listOf(
        R.string.boarding1
              ,
        R.string.boarding2
        ,
        R.string.boarding3
    )
    var currentPage= mutableStateOf(0)
    fun onPageChange(page:Int){
        currentPage.value=page
    }

}