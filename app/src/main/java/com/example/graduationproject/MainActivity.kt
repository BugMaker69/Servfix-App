package com.example.graduationproject
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.graduationproject.test.AnimatedTextField
import com.example.graduationproject.test.CustomTextFieldWithUpload
import com.example.graduationproject.test.DropdownMenuExample
import com.example.graduationproject.test.LocationScreen
import com.example.graduationproject.test.LocationScreen4
//import com.example.graduationproject.test.ExpandableOutlinedTextField
import com.example.graduationproject.test.TextFieldWithInfoIcon
import com.example.graduationproject.test.TopBar

import com.example.graduationproject.ui.theme.GraduationProjectTheme
import android.Manifest

class MainActivity : ComponentActivity() {
    private val REQUEST_LOCATION_PERMISSION = 1

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
                        SignupThirdScreen(Modifier,{},{})
//                        ShareProblem(Modifier,{},{})
//                        BeforeSignup(onBecomeClick = { /*TODO*/ }, onHireClick = { /*TODO*/ }) {}
//                       ServixApp()
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


