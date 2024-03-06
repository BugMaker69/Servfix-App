package com.example.graduationproject

import android.content.Context
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val userSigningLoging: UserViewModel = viewModel()
    val userTypeViewModel: UserTypeViewModel = viewModel()
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
        startDestination = if (isFirstLaunch) ServixScreens.OnBoarding.name else ServixScreens.BeforeSignup.name
    ) {
        composable(ServixScreens.OnBoarding.name) {
            OnBoardingScreen {
                navController.navigate(ServixScreens.Login.name)
            }
        }
        composable(ServixScreens.Login.name) {
            Login(
                onLoginClick = {},
                onSignupClick = {
                    navController.navigate(ServixScreens.BeforeSignup.name)
                },
                onForgetPasswordClick = {
                    navController.navigate(ServixScreens.ForgotPassword.name)
                }
            )
        }
        //  Todo How To Handle ThirdSignUp Page ?!
        composable(ServixScreens.BeforeSignup.name) {
            BeforeSignup(
                onBecomeClick = {
                    navController.navigate(ServixScreens.FirstSignup.name)
                },
                onHireClick = {
                    navController.navigate(ServixScreens.FirstSignup.name)
                },
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                },
                userTypeViewModel = userTypeViewModel
            )
        }
        composable(ServixScreens.Settings.name) {
            SettingsScreen(
                onAccountInfoClick = {
                    if (userTypeViewModel.userType.value == UserType.OwnerPerson) {
                        navController.navigate(ServixScreens.UserAccountInfo.name)
                    } else {
                        navController.navigate(ServixScreens.ProviderAccountInfo.name)
                    }
                },
                onDeleteMyAccountClick = {
                    navController.navigate(ServixScreens.Login.name) {
                        popUpTo(ServixScreens.Settings.name) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onSignOutClick = {
                    navController.navigate(ServixScreens.Login.name) {
                        popUpTo(ServixScreens.Settings.name) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onSecurityClick = {},
                onBackButtonOnTopNavBar = { navController.popBackStack() },
                onBottomNavigationItemClick = {}
            )
        }
        //  Todo How To Handle UserInfo And ProviderInfo ?!
        composable(ServixScreens.UserAccountInfo.name) {
            UserAccountInfoScreen(
                onAccountInfoDetailsClick = {
//                    if (userTypeViewModel.userType.value == UserType.OwnerPerson) {
                        navController.navigate(ServixScreens.UserAccountInfoDetails.name)
//                    } else {
//                        navController.navigate(ServixScreens.ProviderAccountInfoDetails.name)
//                    }
                },
                onBackButtonOnTopNavBar = {
                    navController.popBackStack()
                },
                //  TODO Not Handled Yet It's Wrong
                onBottomNavigationItemClick = {
                    navController.navigate(ServixScreens.Settings.name)
                }
            )
        }
        composable(ServixScreens.UserAccountInfoDetails.name) {
            UserAccountInfoDetailsScreen(
                onBackButtonOnTopNavBar = {
                    navController.popBackStack()
                },
                onBottomNavigationItemClick = {},
                onPhotoChangeClick = {},
                onSaveChangesClick = {}
            )
        }
        composable(ServixScreens.ProviderAccountInfo.name){
            ProviderAccountInfoScreen(
                onAccountInfoDetailsClick = {
                                            navController.navigate(ServixScreens.ProviderAccountInfoDetails.name)
                                            },
                onBackButtonOnTopNavBar = { navController.popBackStack() },
                onBottomNavigationItemClick ={}
            )
        }
        composable(ServixScreens.ProviderAccountInfoDetails.name){
            ProviderAccountInfoDetailsScreen(
                onBackButtonOnTopNavBar = { navController.popBackStack() },
                onBottomNavigationItemClick = {},
                onSaveChangesClick = { /*TODO*/ },
                onPhotoChangeClick = {}
            )
        }
        composable(ServixScreens.FirstSignup.name) {
            SignupFirstScreen(
                userSigningLoging,
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                },
                onNextClick = {
                    navController.navigate(ServixScreens.SecondSignup.name)
                },
                viewModel = userTypeViewModel
            )
        }
        composable(ServixScreens.SecondSignup.name) {
            SignupSecondScreen(
                userSigningLoging,
                onBackClick = {

                    navController.navigate(ServixScreens.FirstSignup.name)
                },

                onFinishClick = {
                    if (userTypeViewModel.userType.value == UserType.HirePerson) {
                        navController.navigate(ServixScreens.SignupThird.name)
                    } else {
                        navController.navigate(ServixScreens.Otp.name)
                    }
                },
                onLoginClick = {
                    navController.navigate(ServixScreens.Login.name)
                },
                viewModel = userTypeViewModel
            )
        }
        composable(ServixScreens.SignupThird.name) {
            SignupThirdScreen(onLoginClick = {
                navController.navigate(ServixScreens.Login.name)
            }, onFinishClick = {
                navController.navigate(ServixScreens.Otp.name)
            }, onBackClick = {
                navController.popBackStack()
            },
                userViewModel = userSigningLoging
            )
        }
        composable(ServixScreens.ForgotPassword.name) {
            FirstScreenOnForgotPasswordChange(
                onSendClick = {
                    navController.navigate(ServixScreens.Otp.name)

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
        composable(ServixScreens.Otp.name) {
            OtpScreen(userSigningLoging) {
                navController.navigate(ServixScreens.Test.name)
            }
        }
        composable(ServixScreens.Test.name) {
            TestScreen()
        }

    }
}