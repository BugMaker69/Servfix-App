package com.example.graduationproject
import ChatContactScreen
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

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
    fun MainScreen(data:Uri) {
        val networkStatus by networkConnectivityObserver.observe().collectAsState(initial = InternetObserver.Status.Loading)

        Log.d("oooo", "MainScreen: $networkStatus")
        when (networkStatus) {
            InternetObserver.Status.Available -> {
                ServixApp(data)
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


        var d by mutableStateOf(Uri.EMPTY)

        // Handle deep link intent
        intent?.data?.let { uri ->
            d  = uri
/*            Log.d("DeepLink", "Received deep link: ${d}")
            Log.d("DeepLink", "Received deep link: $uri")
            Log.d("DeepLink", "Received deep link: $uri")
            val resetToken = uri.lastPathSegment
            Log.d("DeepLink", "Reset token: $resetToken")
            val resetToken1 = uri.path
            Log.d("DeepLink", "Reset token: $resetToken1")
            val resetToken2 = uri.pathSegments
            Log.d("DeepLink", "Reset token: $resetToken2")
            val resetToken3 = uri.host
            Log.d("DeepLink", "Reset token: $resetToken3")
            // Handle resetToken, navigate to appropriate screen, etc.*/
        }


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
/*                        val data = intent.data
                        if (data != null){
                            val host = data.host
                            val scheme = data.scheme
                            val path = data.path
                            val url = "$scheme://$host$path"
                            Log.d("URL", "onCreate: $url")
                            Toast.makeText(this@MainActivity,"$url",Toast.LENGTH_SHORT).show()
                        }*/
                       // FavoriteScreen(Modifier.padding(PaddingValues(start=0.0.dp, top=64.0.dp, end=0.0.dp, bottom=80.0.dp)))
//                        AnimatedTextField()
//                        SignupThirdScreen(Modifier,{},{})
//                        ShareProblem(Modifier,{},{})
//                        BeforeSignup(onBecomeClick = { /*TODO*/ }, onHireClick = { /*TODO*/ }) {}
                     //   TestScreenForApi()
                        MainScreen(d)                   //     x()
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
/*    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let { uri ->
            Log.d("DeepLink", "Received new intent with deep link: $uri")
            val resetToken = uri.lastPathSegment
            Log.d("DeepLink", "Reset token: $resetToken")
            // Handle resetToken, navigate to appropriate screen, etc.
        }
    }*/
}


