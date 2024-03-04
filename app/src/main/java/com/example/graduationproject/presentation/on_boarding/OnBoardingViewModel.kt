package com.example.graduationproject.presentation.on_boarding

import androidx.compose.runtime.mutableStateOf


import androidx.lifecycle.ViewModel
import com.example.graduationproject.R

class OnBoardingViewModel() : ViewModel() {
    val animations = listOf(
        R.raw.worker, R.raw.workers2, R.raw.location
    )
    val descriptin = listOf(
        "blabla blala bla bla bla bla bla bla bla bla",
        "go lopdsk rekokw Lorem polrism etoro lol bor bk",
        "go test test fersha ramadan we el saadany to7yeekom test test test Al3aaaab \uD83D\uDD25\uD83D\uDD25\uD83D\uDE0E"
    )
    var currentPage= mutableStateOf(0)
    fun onPageChange(page:Int){
        currentPage.value=page
    }

}