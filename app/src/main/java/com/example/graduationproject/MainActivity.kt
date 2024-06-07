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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
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

        intent?.data?.let { uri ->
            d  = uri

        }

        installSplashScreen()
        setContent {
            GraduationProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                    ) {

                        ServixApp(d)

                    }

                }
            }
        }
    }
}


