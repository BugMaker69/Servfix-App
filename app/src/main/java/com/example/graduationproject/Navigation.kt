package com.example.graduationproject

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.graduationproject.ui.OnBoardingScreen
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
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)
    LaunchedEffect(Unit) {
        if (isFirstLaunch) {
            navController.navigate(ServixScreens.OnBoarding.name) {
                popUpTo(ServixScreens.OnBoarding.name) { inclusive = true }
                launchSingleTop = true
            }
            sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()


        }
    }
    NavHost(
        navController = navController,
        startDestination = if (isFirstLaunch) ServixScreens.OnBoarding.name else ServixScreens.Login.name
    ) {
composable(ServixScreens.OnBoarding.name){
    OnBoardingScreen {
        navController.navigate(ServixScreens.Login.name)
    }
}
        composable(ServixScreens.Login.name) {
            Login(
                onLoginClick = {},
                onSignupClick = {
                    navController.navigate(ServixScreens.FirstSignup.name + "/{phoneNum}")
                },
                onForgetPasswordClick = {
                    navController.navigate(ServixScreens.ForgotPassword.name+ "/{phoneNum}")
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

                    navController.navigate(ServixScreens.SecondSignup.name + "/$it")
                },
            )
        }
        composable(
            ServixScreens.SecondSignup.name + "/{phoneNum}",
            arguments = listOf(navArgument("phoneNum") {
                type = NavType.StringType
            })
        ) {
            SignupSecondScreen(
                onBackClick = {

                    navController.navigate(ServixScreens.FirstSignup.name + "/${it.arguments?.getString("phoneNum")}")
                },

                onFinishClick = {
                    navController.navigate(ServixScreens.Otp.name + "/${it.arguments?.getString("phoneNum")}")
                }
            )
        }
        composable(
            ServixScreens.ForgotPassword.name + "/{phoneNum}",
            arguments = listOf(navArgument("phoneNum") {
                type = NavType.StringType
            })
        ) {
            FirstScreenOnForgotPasswordChange(
                onSendClick = {
                    navController.navigate(ServixScreens.Otp.name + "/${it.arguments?.getString("phoneNum")}")

                },
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


//NavHost(
//navController = navController,
//startDestination = ServixScreens.Login.name,
//) {
//    composable(ServixScreens.Login.name)
//    {
//        Login(
//            onLoginClick = {
//            },
//            onSignupClick = {
//                navController.navigate(ServixScreens.Signup.name+"/{phoneNum}") {
//                    popUpTo(ServixScreens.Login.name) { inclusive = true }
//                    launchSingleTop = true
//                }
//            },
//            onForgetPasswordClick = {}
//        )
//    }
//    composable(ServixScreens.Signup.name + "/{phoneNum}",
//        arguments = listOf(navArgument("phoneNum") {
//            type = NavType.StringType
//        })
//    ) {
//        Signup(
//            onSignupClick = {
//                navController.navigate(ServixScreens.Otp.name + "/$it")
//            },
//            onLoginClick = {
//                navController.navigate(ServixScreens.Login.name) {
//                    popUpTo(ServixScreens.Signup.name) { inclusive = true }
//                    launchSingleTop = true
//                }
//            })
//    }
//    composable(ServixScreens.Otp.name + "/{phoneNum}",
//        arguments = listOf(navArgument("phoneNum") {
//            type = NavType.StringType
//        })
//    ) {
//        OtpScreen(){
//            navController.navigate(ServixScreens.Test.name)
//        }
//    }
//    composable(ServixScreens.Test.name){
//        TestScreen()
//    }
//}
//}