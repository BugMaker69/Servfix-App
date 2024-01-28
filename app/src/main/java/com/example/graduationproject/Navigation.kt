package com.example.graduationproject

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.graduationproject.ui.ServixScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@Composable
fun ServixApp(
    navController: NavHostController = rememberNavController()
) {

    var isBackPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    BackHandler {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        } else if (!isBackPressedOnce) {
            coroutineScope.launch {
                isBackPressedOnce = true
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
                delay(2000)
                isBackPressedOnce = false
            }
        } else {
            exitProcess(0)
        }
    }



    NavHost(
        navController = navController,
        startDestination = ServixScreens.Login.name,
    ) {
        composable(ServixScreens.Login.name) {
            Login(
                onLoginClick = {},
                onSignupClick = {
                    navController.navigate(ServixScreens.Signup.name) {
                        popUpTo(ServixScreens.Login.name) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onForgetPasswordClick = {}
            )
        }
        composable(ServixScreens.Signup.name) {
            Signup(
                onSignupClick = {},
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name) {
                        popUpTo(ServixScreens.Signup.name) { inclusive = true }
                        launchSingleTop = true
                    }
                })
        }
    }
}