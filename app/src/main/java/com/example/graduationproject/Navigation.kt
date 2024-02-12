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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graduationproject.ui.OtpScreen
import com.example.graduationproject.ui.ServixScreens
import com.example.graduationproject.ui.TestScreen
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
                    navController.navigate(ServixScreens.FirstSignup.name + "/{phoneNum}")
                },
                onForgetPasswordClick = {
                    navController.navigate(ServixScreens.ForgotPassword.name)
                }
            )
        }
        composable(
            ServixScreens.FirstSignup.name + "/{phoneNum}",
            arguments = listOf(navArgument("phoneNum") {
                type = NavType.StringType
            })
        ) {
            SignupFirstScreen(
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                },
                onNextClick = {
                    navController.navigate(ServixScreens.SecondSignup.name)
                },
            )
        }
        composable(ServixScreens.SecondSignup.name) {
            SignupSecondScreen(
                onBackClick = {
                    navController.navigate(ServixScreens.FirstSignup.name)
                },
                onFinishClick = {
                    navController.navigate(ServixScreens.Otp.name + "$it")
                }
            )
        }
        composable(ServixScreens.ForgotPassword.name) {
            FirstScreenOnForgotPasswordChange(
                onSendClick = {},
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                }
            )
        }
        composable(ServixScreens.ResetPassword.name) {
            ResetPassword(
                onResetClick = {
                    navController.navigate(ServixScreens.AfterPassword.name)
                }
            )
        }
        composable(ServixScreens.AfterPassword.name) {
            AfterPasswordChange(
                onBackToLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                }
            )
        }

        composable(
            ServixScreens.Otp.name + "/{phoneNum}",
            arguments = listOf(navArgument("phoneNum") {
                type = NavType.StringType
            })
        ) {
            OtpScreen() {
                navController.navigate(ServixScreens.Test.name)
            }
        }

        composable(ServixScreens.Test.name) {
            TestScreen()
        }

    }


}
