package com.example.graduationproject
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.graduationproject.presentation.search_for_provider.FindProvider


import com.example.graduationproject.ui.theme.GraduationProjectTheme
import com.example.graduationproject.ui.ServixApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GraduationProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                    ) {
//                        AnimatedTextField()
//                        SignupThirdScreen(Modifier,{},{})
//                        ShareProblem(Modifier,{},{})
//                        BeforeSignup(onBecomeClick = { /*TODO*/ }, onHireClick = { /*TODO*/ }) {}
                       ServixApp()
                     //   FindProvider(Modifier,{},{},{})
                        //OnBoardingScreen(){
//                        ServicesHomePage()
//                        ExpandableOutlinedTextField(
//                            placeholderText = "Search",
//                            fontSize = 14.sp
//                        )
                    }
                    //   OtpScreen(LocalContext.current)
                    //    OtpTextField()

                }
            }
        }
    }
}


