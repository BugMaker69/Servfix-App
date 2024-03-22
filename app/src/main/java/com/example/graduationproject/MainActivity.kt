package com.example.graduationproject
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.graduationproject.data.retrofit.TestScreenForApi
import com.example.graduationproject.presentation.favourite.FavoriteScreen
import com.example.graduationproject.presentation.search_for_provider.FindProvider
import com.example.graduationproject.presentation.userservice.UserHomeScreen


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
                       // FavoriteScreen(Modifier.padding(PaddingValues(start=0.0.dp, top=64.0.dp, end=0.0.dp, bottom=80.0.dp)))
//                        AnimatedTextField()
//                        SignupThirdScreen(Modifier,{},{})
//                        ShareProblem(Modifier,{},{})
//                        BeforeSignup(onBecomeClick = { /*TODO*/ }, onHireClick = { /*TODO*/ }) {}
                     //   TestScreenForApi()
                      ServixApp()
//                        UserHomeScreen(
//                            onNotificationClick = { /*TODO*/ },
//                            onMessageClick = { /*TODO*/ },
//                            onTextFieldClick = { /*TODO*/ },
//                            onServiceItemClick = { /*TODO*/ },
//                            onBottomNavigationItemClick = {}
//                        )
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


