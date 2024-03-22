package com.example.graduationproject.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graduationproject.BeforeSignup
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoDetailsScreen
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoScreen
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoDetailsScreen
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoScreen
import com.example.graduationproject.presentation.common.HomeTopBar
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.presentation.common.UserTypeViewModel
import com.example.graduationproject.presentation.common.login.AfterPasswordChange
import com.example.graduationproject.presentation.common.login.FirstScreenOnForgotPasswordChange
import com.example.graduationproject.presentation.common.login.Login
import com.example.graduationproject.presentation.common.login.ResetPassword
import com.example.graduationproject.presentation.common.settings.SettingsScreen
import com.example.graduationproject.presentation.common.settings.SettingsTopBar
import com.example.graduationproject.presentation.common.signup.SignupFirstScreen
import com.example.graduationproject.presentation.common.signup.SignupSecondScreen
import com.example.graduationproject.presentation.common.signup.SignupThirdScreen
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.presentation.favourite.FavoriteScreen
import com.example.graduationproject.presentation.notification.NotificationScreen
import com.example.graduationproject.presentation.notification.NotificationTopBar
import com.example.graduationproject.presentation.on_boarding.OnBoardingScreen
import com.example.graduationproject.presentation.otp.OtpScreen
import com.example.graduationproject.presentation.search_for_provider.FindProvider
import com.example.graduationproject.presentation.userservice.ShareProblemScreen
import com.example.graduationproject.presentation.userservice.UserHomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServixApp(
    navController: NavHostController = rememberNavController()
) {
    val userViewModel: UserViewModel = viewModel()
    val userTypeViewModel: UserTypeViewModel = viewModel()
    var isBackPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


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

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            when (currentRoute) {
                ServixScreens.ProviderAccountInfo.name, ServixScreens.ProviderAccountInfoDetails.name, ServixScreens.UserAccountInfo.name -> {
                    SettingsTopBar(onBackButtonOnTopNavBar = { }, showBack = true)
                }

                ServixScreens.Settings.name -> {
                    SettingsTopBar(onBackButtonOnTopNavBar = { }, showBack = false)
                }

                ServixScreens.UserHomeScreen.name, ServixScreens.FindProvider.name + "/{id}", ServixScreens.Favorite.name -> HomeTopBar(
                    onNotificationClick = { navController.navigate(ServixScreens.Notification.name) },
                    onMessageClick = { },
                    scrollBarBehavior = scrollBehavior
                )

                ServixScreens.Notification.name -> NotificationTopBar { }


            }

        },

        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            if (currentRoute in listOf(
                    ServixScreens.Settings.name,
                    ServixScreens.Favorite.name,
                    ServixScreens.UserHomeScreen.name,
                    ServixScreens.ProviderAccountInfo.name,
                    ServixScreens.ProviderAccountInfoDetails.name,
                    ServixScreens.FindProvider.name + "/{id}"
                )
            )
                BottomAppBar(onBottomNavigationItemClick = {}, navController = navController)
        }
    ) { innerPadding ->
        Log.d("oooook", "ServixApp: ${innerPadding.toString()}")
        NavHost(
            navController = navController,
            startDestination = if (isFirstLaunch) ServixScreens.OnBoarding.name else ServixScreens.Login.name
        ) {
            composable(ServixScreens.OnBoarding.name) {
                OnBoardingScreen {
                    navController.navigate(ServixScreens.Login.name)
                }
            }
            composable(
                ServixScreens.FindProvider.name + "/{id}", arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    })
            ) {
                FindProvider(
                    modifier = Modifier.padding(innerPadding),
                    onNotificationClick = { /*TODO*/ },
                    onMessageClick = { /*TODO*/ },
                    {})
            }
            composable(ServixScreens.Favorite.name) {
                FavoriteScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(ServixScreens.Login.name) {
                Login(
                    onLoginClick = {
                        userViewModel.login()

                        navController.navigate(ServixScreens.UserHomeScreen.name)
                    },
                    onSignupClick = {
                        navController.navigate(ServixScreens.BeforeSignup.name)
                    },
                    onForgetPasswordClick = {
                        navController.navigate(ServixScreens.ForgotPassword.name)
                    },
                    userViewModel = userViewModel
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
                SettingsScreen(innerPadding,
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
                    onBottomNavigationItemClick = {
                        navController.navigate(it)
                        Log.d("NAVHOST", "ServixApp NAVHOST: $it")
                    }
                )
            }
            //  Todo How To Handle UserInfo And ProviderInfo ?!
            composable(ServixScreens.UserAccountInfo.name) {
                UserAccountInfoScreen(innerPadding,
                    onAccountInfoDetailsClick = {
                        navController.navigate(ServixScreens.UserAccountInfoDetails.name)
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
                    innerPadding = innerPadding,
                    onBackButtonOnTopNavBar = {
                        navController.popBackStack()
                    },
                    onBottomNavigationItemClick = {},
                    onPhotoChangeClick = {},
                    onSaveChangesClick = {
                        navController.navigate(ServixScreens.Settings.name)
                    }
                )
            }
            composable(ServixScreens.ProviderAccountInfo.name) {
                ProviderAccountInfoScreen(
                    innerPadding = innerPadding,
                    onAccountInfoDetailsClick = {
                        navController.navigate(ServixScreens.ProviderAccountInfoDetails.name)
                    },
                    onBackButtonOnTopNavBar = { navController.popBackStack() },
                    onBottomNavigationItemClick = {}
                )
            }
            composable(ServixScreens.ProviderAccountInfoDetails.name) {
                ProviderAccountInfoDetailsScreen(innerPadding = innerPadding,
                    onBackButtonOnTopNavBar = { navController.popBackStack() },
                    onBottomNavigationItemClick = {},
                    onSaveChangesClick = { /*TODO*/ },
                    onPhotoChangeClick = {}
                )
            }
            composable(ServixScreens.FirstSignup.name) {
                SignupFirstScreen(
                    userViewModel,
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
                    userViewModel,
                    onBackClick = {

                        navController.navigate(ServixScreens.FirstSignup.name)
                    },

                    onFinishClick = {
                        if (userTypeViewModel.userType.value == UserType.HirePerson) {
                            navController.navigate(ServixScreens.SignupThird.name)
                        } else {
                            userViewModel.registerUser()

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
                    userViewModel = userViewModel
                )
            }
            composable(ServixScreens.ForgotPassword.name) {
                FirstScreenOnForgotPasswordChange(
                    onSendClick = {
                        navController.navigate(ServixScreens.Otp.name)

                    },
                    onLoginClick = {
                        navController.navigate(ServixScreens.Login.name)
                    },
                    userViewModel = userViewModel
                )
            }
            composable(ServixScreens.ResetPassword.name) {
                ResetPassword(
                    onResetClick = {
                        navController.navigate(ServixScreens.AfterPassword.name)
                    },
                    userViewModel = userViewModel
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
                OtpScreen(
                    userViewModel = userViewModel,
                    onLoginClick = {
                        if (userViewModel.isPasswordForget) {
                            navController.navigate(ServixScreens.AfterPassword.name)
                        } else {
                            navController.navigate(ServixScreens.UserHomeScreen.name)
                        }
                    }
                )
            }
            composable(ServixScreens.UserHomeScreen.name) {
                UserHomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNotificationClick = {
                        navController.navigate(ServixScreens.Notification.name)
                    },
                    onMessageClick = { /*TODO*/ },
                    onTextFieldClick = {
                        navController.navigate(ServixScreens.ShareProblemScreen.name)
                    },
                    onServiceItemClick = { id ->
                        navController.navigate(ServixScreens.FindProvider.name + "/$id")

                    },
                    //  TODO WHen Navigate the Screen Change But BottomBar Icon Not Selected Correctly
                    onBottomNavigationItemClick = {
                        navController.navigate(it)
                        Log.d("NAVHOST", "ServixApp NAVHOST: $it")
                    }
                )
            }
            composable(ServixScreens.Notification.name) {
                NotificationScreen(
                    modifier = Modifier.padding(innerPadding),
                    onBackButtonOnTopNavBar = { navController.popBackStack() },
                    onNotificationItemClick = { /*TODO*/ },
                    notificationDescription = ""
                )
            }
            composable(ServixScreens.ShareProblemScreen.name) {
                ShareProblemScreen(
                    onCancelClick = { navController.popBackStack() },
                    onShareClick = { navController.navigate(ServixScreens.UserHomeScreen.name) })
            }
            composable(ServixScreens.Test.name) {
                TestScreen()
            }

        }
    }
}