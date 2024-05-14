package com.example.graduationproject
import ChatContactScreen
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.graduationproject.data.retrofit.InternetObserver
import com.example.graduationproject.data.retrofit.NetworkConnectivityObserver
import com.example.graduationproject.data.retrofit.NoInternetScreen
import com.example.graduationproject.presentation.LoadingScreen

import com.example.graduationproject.ui.theme.GraduationProjectTheme
import com.example.graduationproject.ui.ServixApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val networkConnectivityObserver = NetworkConnectivityObserver(MyApplication.getApplicationContext())
    @SuppressLint("FlowOperatorInvokedInComposition")
    @Composable
    fun MainScreen() {
        val networkStatus by networkConnectivityObserver.observe().collectAsState(initial = InternetObserver.Status.Loading)

        Log.d("oooo", "MainScreen: $networkStatus")
        when (networkStatus) {
            InternetObserver.Status.Available -> {
                ServixApp()
            }
            InternetObserver.Status.NotAvailable -> {
                NoInternetScreen()
            }
            InternetObserver.Status.Loading->{
                LoadingScreen()}

        }
    }


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
                        MainScreen()                   //     x()
                //        ChatContactScreen()
//                        val sampleMessages = listOf(
//                            Message(1, "Hello!", false),
//                            Message(2, "Hi! How are you?", true),
//                            Message(3, "I'm good, thanks for asking.", false)
//                        )
//                       ChatScreen(messages =sampleMessages )

                     //   ViewProfileScreen(modifier = Modifier.padding(20.dp
                      //  ), viewProfileViewModel = ViewProfileViewModel() )
                //        FavoriteScreen(modifier = Modifier)

                     //   FavoriteScreen(modifier = )
                  //      ImagePickerScreen()
//                        UserHomeScreen(
//                            onNotificationClick = { /*TODO*/ },
//                            onMessageClick = { /*TODO*/ },
//                            onTextFieldClick = { /*TODO*/ },
//                            onServiceItemClick = { /*TODO*/ },
//                            onBottomNavigationItemClick = {}
//                        )
                 //  FindProvider(Modifier)
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


