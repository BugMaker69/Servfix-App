package com.example.graduationproject.data.retrofit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R


//@Composable
//fun x() {
//    val context = LocalContext.current
//    Button(onClick = {
//        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
//            data = Uri.parse("tel:01025659292")
//        }
//            context.startActivity(dialIntent)
//            Log.d("data", "x: ")
//
//    }) {
//        Text("Open Dialer")
//    }
//}
@Composable
fun NoInternetScreen(){

    Column(Modifier.padding(30.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(imageVector = Icons.Filled.WifiOff, contentDescription ="No Internet" ,modifier= Modifier
            .size(120.dp))

        Text(text = stringResource(id = R.string.No_Internet) , fontWeight = FontWeight.ExtraBold, fontSize = 16.sp,modifier=Modifier.align(Alignment.CenterHorizontally), textAlign = TextAlign.Center)
    }
}