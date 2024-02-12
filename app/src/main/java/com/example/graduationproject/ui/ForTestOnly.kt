package com.example.graduationproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun TestScreen(){
    Column(Modifier.fillMaxSize()) {
        Text(text = "Don't worry it is just a test")
    }
}