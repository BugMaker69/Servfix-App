package com.example.graduationproject.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SeeWorkViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
): ViewModel(){

    var imageId by mutableStateOf(0)
    var imageWork by mutableStateOf("")

    init {
        imageId = savedStateHandle.get<Int>("pid") ?: 0
        imageWork = savedStateHandle.get<String>("pname") ?: ""
        Log.d("savedStateHandle Image ID", "savedStateHandle Image ID : $imageId")
        Log.d("savedStateHandle Image ID", "savedStateHandle Image ID : $imageWork")
    }

}