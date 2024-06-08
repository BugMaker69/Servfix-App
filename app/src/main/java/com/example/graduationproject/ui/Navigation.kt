package com.example.graduationproject.ui

import ChatContactScreen
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graduationproject.MyApplication
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.presentation.AddNeWorkToProfileItems
import com.example.graduationproject.presentation.LoadingScreen
import com.example.graduationproject.presentation.SeeWork
import com.example.graduationproject.presentation.SeeWorkViewModel
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoDetailsScreen
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoScreen
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoViewModel
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoDetailsScreen
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoScreen
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoViewModel
import com.example.graduationproject.presentation.chat.ChatContactViewModel
import com.example.graduationproject.presentation.chat.ChatScreen
import com.example.graduationproject.presentation.chat.ChatViewModel
import com.example.graduationproject.presentation.common.HomeTopBar
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.presentation.common.UserTypeViewModel
import com.example.graduationproject.presentation.common.login.AfterPasswordChange
import com.example.graduationproject.presentation.common.login.FirstScreenOnForgotPasswordChange
import com.example.graduationproject.presentation.common.login.Login
import com.example.graduationproject.presentation.common.login.ResetPassword
import com.example.graduationproject.presentation.common.settings.NewPasswordScreen
import com.example.graduationproject.presentation.common.settings.SettingsScreen
import com.example.graduationproject.presentation.common.settings.SettingsTopBar
import com.example.graduationproject.presentation.common.settings.SettingsViewModel
import com.example.graduationproject.presentation.common.signup.BeforeSignup
import com.example.graduationproject.presentation.common.signup.SignupFirstScreen
import com.example.graduationproject.presentation.common.signup.SignupSecondScreen
import com.example.graduationproject.presentation.common.signup.SignupThirdScreen
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.presentation.favourite.FavoriteScreen
import com.example.graduationproject.presentation.favourite.FavouriteViewModel
import com.example.graduationproject.presentation.notification.NotificationDetails
import com.example.graduationproject.presentation.notification.NotificationScreen
import com.example.graduationproject.presentation.notification.NotificationTopBar
import com.example.graduationproject.presentation.notification.NotificationViewModel
import com.example.graduationproject.presentation.on_boarding.OnBoardingScreen
import com.example.graduationproject.presentation.otp.OtpScreen
import com.example.graduationproject.presentation.provider_home.PostDetailViewModel
import com.example.graduationproject.presentation.provider_home.PostItemDetail
import com.example.graduationproject.presentation.provider_home.ProviderHomeViewModel
import com.example.graduationproject.presentation.provider_home.ProviderPostScreen
import com.example.graduationproject.presentation.search_for_provider.FindProvider
import com.example.graduationproject.presentation.search_for_provider.FindProviderViewModel
import com.example.graduationproject.presentation.share_problem.ShareProblemSpecific
import com.example.graduationproject.presentation.share_problem.ShareProblemSpecificViewModel
import com.example.graduationproject.presentation.userservice.ServiceViewModel
import com.example.graduationproject.presentation.userservice.ShareProblemScreen
import com.example.graduationproject.presentation.userservice.UserHomeScreen
import com.example.graduationproject.presentation.viewprofile.ViewProfileScreen
import com.example.graduationproject.presentation.viewprofile.ViewProfileViewModel
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState",
    "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServixApp(
    intentUriData:Uri,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val userTypeViewModel: UserTypeViewModel = viewModel()
    var isBackPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val dataStoreToken = DataStoreToken()
    var isLoggedIn = runBlocking { dataStoreToken.getLogin() }
    val userTypeFlow = dataStoreToken.userType.collectAsState(initial = "")
    val notificationViewModel: NotificationViewModel = hiltViewModel()

    Log.d("vvv", "ServixApp:up ${userTypeFlow.value} ")
    Log.d("qqqqqqqqq", "ServixApp: ${isLoggedIn}")


        LaunchedEffect(isLoggedIn,userTypeFlow) {
            Log.d("problem", "ServixApp: entered launchedeffect1")
        val loginResult = dataStoreToken.getLogin()
        isLoggedIn = loginResult

            if (intentUriData.host=="p2kjdbr8-8000.uks1.devtunnels.ms"){

                navController.navigate(ServixScreens.ResetPassword.name)
            }

        // Check login state and user type after data retrieval
       else if (isLoggedIn && userTypeFlow.value != "") {
            Log.d("mmmm1", "ServixApp: ${userTypeFlow}")
            if (userTypeFlow.value == UserType.OwnerPerson.name) {
                navController.navigate(ServixScreens.HomeUser.name) {
                    popUpTo(ServixScreens.OnBoarding.name) { inclusive = true }
                    launchSingleTop = true
                }
            } else if (userTypeFlow.value == UserType.HirePerson.name) {
                navController.navigate(ServixScreens.HomeProvider.name) {
                    popUpTo(ServixScreens.OnBoarding.name) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }


    BackHandler {
        if (navController.previousBackStackEntry != null) {
            if (navController.currentBackStackEntry?.destination?.route != ServixScreens.Login.name) {
                navController.popBackStack()
            }
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
            when (navController.currentBackStackEntryAsState().value?.destination?.route) {
                ServixScreens.NewPasswordScreen.name, ServixScreens.ProviderAccountInfo.name, ServixScreens.ProviderAccountInfoDetails.name, ServixScreens.UserAccountInfo.name -> {
                    SettingsTopBar(
                        onBackButtonOnTopNavBar = {
                            navController.popBackStack()
                        },
                        showBack = true,
                        scrollBarBehavior = scrollBehavior
                    )
                }

                ServixScreens.NewPasswordScreen.name, ServixScreens.Settings.name -> {
                    SettingsTopBar(
                        onBackButtonOnTopNavBar = { navController.popBackStack() },
                        showBack = false,
                        scrollBarBehavior = scrollBehavior
                    )
                }

                ServixScreens.PostDetails.name + "/{id}", ServixScreens.ChatContactScreen.name, ServixScreens.PostDetails.name + "/{id}", ServixScreens.HomeUser.name, ServixScreens.HomeProvider.name, ServixScreens.FindProvider.name + "/{id}" + "/{serviceName}", ServixScreens.Favorite.name -> HomeTopBar(
                    onNotificationClick = { navController.navigate(ServixScreens.Notification.name) },
                    onMessageClick = { },
                    scrollBarBehavior = scrollBehavior
                )

                ServixScreens.Notification.name -> NotificationTopBar {
                    navController.popBackStack()
                }


            }

        },

        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            if (currentRoute in listOf(
                    ServixScreens.Notification.name,
                    ServixScreens.Settings.name,
                    ServixScreens.Favorite.name,
                    ServixScreens.HomeUser.name,
                    ServixScreens.ViewProfile.name + "/{pid}",
                    ServixScreens.ProviderAccountInfo.name,
                    ServixScreens.ProviderAccountInfoDetails.name,
                    ServixScreens.FindProvider.name + "/{id}" + "/{serviceName}",
                    ServixScreens.HomeProvider.name,
                    ServixScreens.ChatContactScreen.name,
                    ServixScreens.PostDetails.name + "/{id}",
                )
            ) {
                BottomAppBar(navController = navController, userTypeFlow.value)

            }
        }
    ) { innerPadding ->
        val userViewModel: UserViewModel = hiltViewModel()


        NavHost(
            navController = navController,
            startDestination =
            if (isFirstLaunch) ServixScreens.OnBoarding.name
            else if (intentUriData.host=="p2kjdbr8-8000.uks1.devtunnels.ms"){

                ServixScreens.ResetPassword.name}
            else {
                if (isLoggedIn && userTypeFlow.value.isNotBlank()) {
                    Log.d("mmmm1", "ServixApp: ${userTypeFlow.value}")
                    if (userTypeFlow.value == UserType.OwnerPerson.name) ServixScreens.HomeUser.name else ServixScreens.HomeProvider.name
                }
                else if(isLoggedIn&&userTypeFlow.value.isBlank()){
                    ServixScreens.LoadingScreen.name
                }

                else {
                    Log.d("mmmm6", "ServixApp: ${userTypeFlow.value}")
                    ServixScreens.Login.name
                }
            }
        ) {

/*            if (data=="p2kjdbr8-8000.uks1.devtunnels.ms"){
                navController.navigate(ServixScreens.ResetPassword.name)
            }*/

            composable(ServixScreens.OnBoarding.name) {
                OnBoardingScreen {
                    navController.navigate(ServixScreens.Login.name)
                }
            }
            composable(
                ServixScreens.FindProvider.name + "/{id}" + "/{serviceName}", arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }, navArgument("serviceName") {
                        type = NavType.StringType
                    }
                )
            ) {
                val findProviderViewModel: FindProviderViewModel = hiltViewModel()

                FindProvider(
                    modifier = Modifier.padding(innerPadding),
                    viewProfileClick = { pid ->
                        navController.navigate(ServixScreens.ViewProfile.name + "/$pid")
                    },
                    findProviderViewModel = findProviderViewModel
                )
            }
            composable(
                ServixScreens.ViewProfile.name + "/{pid}", arguments = listOf(
                    navArgument("pid") {
                        type = NavType.IntType
                    })
            ) {
                val viewProfileViewModel: ViewProfileViewModel = hiltViewModel()


                ViewProfileScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewProfileViewModel = viewProfileViewModel,
                    onChatClick = { pid, providerName ->
                        navController.navigate(ServixScreens.ShareProblemSpecific.name + "/$pid" + "/$providerName")
                    }
                )

            }

            composable(ServixScreens.ShareProblemSpecific.name + "/{pid}" + "/{providerName}",
                arguments = listOf(
                    navArgument("pid") {
                        type = NavType.IntType
                    },
                    navArgument("providerName") {
                        type = NavType.StringType
                    }
                )) {
                val serviceViewmodel: ShareProblemSpecificViewModel = hiltViewModel()
                var show by remember { mutableStateOf(false) }

                ShareProblemSpecific(
                    modifier = Modifier.padding(innerPadding),
                    onCancelClick = { navController.popBackStack() },
                    onShareClick = {
                        coroutineScope.launch {
                            serviceViewmodel.shareSpecificPost()
                            show=true
                            navController.navigate(ServixScreens.HomeUser.name)
                        }


                    },
                    serviceViewModel = serviceViewmodel

                )
                LaunchedEffect(key1 = show) {

                    Toast.makeText(MyApplication.getApplicationContext().applicationContext, "done", Toast.LENGTH_SHORT).show()
                    // Reset the show flag to prevent re-displaying the Toast

                }

            }

            composable(ServixScreens.Favorite.name) {
                val favouriteViewModel: FavouriteViewModel = hiltViewModel()

                FavoriteScreen(
                    modifier = Modifier.padding(innerPadding),
                    favouriteViewModel = favouriteViewModel,
                    onProfileClicked = { id ->
                        navController.navigate(ServixScreens.ViewProfile.name + "/$id")

                    }
                )
            }
            composable(ServixScreens.Login.name) {

                    Login(
                        onLoginClick = {
                            coroutineScope.launch {
                                userViewModel.loginEnabled=false
                                userViewModel.login()
                                delay(4000)
                                if (dataStoreToken.getToken() != "") {
                                    Log.d("vvv", "ServixApp: ${userTypeFlow}")
                                    if (userTypeFlow.value== UserType.HirePerson.name) {
                                        navController.navigate(ServixScreens.HomeProvider.name) {
                                            popUpTo(ServixScreens.Login.name) {
                                                inclusive = true
                                            }

                                        }
                                    } else if (userTypeFlow.value == UserType.OwnerPerson.name) {
                                        navController.navigate(ServixScreens.HomeUser.name) {
                                            popUpTo(ServixScreens.Login.name) {
                                                inclusive = true
                                            }

                                        }
                                    }

                            }
                                userViewModel.loginEnabled=true


                            }


                        },
                    onSignupClick = {
                        navController.navigate(ServixScreens.BeforeSignup.name)
                    },
                    onForgetPasswordClick = {
                        navController.navigate(ServixScreens.ForgotPassword.name)
                    },
                    userViewModel = userViewModel, modifier = Modifier.padding(innerPadding)
                )
            }
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
                val settingsViewModel: SettingsViewModel = hiltViewModel()
                SettingsScreen(
                    innerPadding,
                    onAccountInfoClick = {
                        /*                        //TODO NEED TO BE HANDLED IN BETTER WAY USING SUCH DATA STORE
                        userViewModel.getData()*/
                        if (userTypeFlow.value == UserType.OwnerPerson.name) {
                            navController.navigate(ServixScreens.UserAccountInfo.name)
                        } else {
                            navController.navigate(ServixScreens.ProviderAccountInfo.name)
                        }
                    },
                    onDeleteMyAccountClick = {
                        settingsViewModel.deleteAccount()
                        runBlocking {
                            dataStoreToken.saveLoginState(false)
                            dataStoreToken.saveToken("")
                            dataStoreToken.saveUserType("") // Clear user type as well
                        }
//                        navController.navigate(ServixScreens.Login.name) {
//                            popUpTo(ServixScreens.Settings.name) {
//                                inclusive = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    },
                    },
                    onSignOutClick = {
                        runBlocking {
                            dataStoreToken.saveLoginState(false)
                            dataStoreToken.saveToken("")
                            dataStoreToken.saveUserType("")
                            navController.navigate(ServixScreens.Login.name) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }



                    },
                    settingsViewModel = settingsViewModel,

                    onSecurityClick = {
                        navController.navigate(ServixScreens.NewPasswordScreen.name)
                    }, modifier = Modifier.padding(innerPadding)
                )
            }
            //  Todo How To Handle UserInfo And ProviderInfo ?!
            composable(ServixScreens.UserAccountInfo.name) {
                val userAccountInfoViewModel: UserAccountInfoViewModel = hiltViewModel()

                UserAccountInfoScreen(
                    innerPadding,
                    onAccountInfoDetailsClick = {
                        navController.navigate(ServixScreens.UserAccountInfoDetails.name)
                    },
                    onBackButtonOnTopNavBar = {
                        navController.popBackStack()
                    },
                    //  TODO Not Handled Yet It's Wrong
                    onBottomNavigationItemClick = {
                        navController.navigate(ServixScreens.Settings.name)
                    },
                    userAccountInfoViewModel = userAccountInfoViewModel
                )
            }
            composable(ServixScreens.UserAccountInfoDetails.name) {
                val userAccountInfoViewModel: UserAccountInfoViewModel = hiltViewModel()

                UserAccountInfoDetailsScreen(
                    innerPadding = innerPadding,
                    onBackButtonOnTopNavBar = {
                        navController.popBackStack()
                    },
                    onBottomNavigationItemClick = {},
                    onPhotoChangeClick = {},
                    onSaveChangesClick = {
                        userAccountInfoViewModel.updateUserData()
                        navController.navigate(ServixScreens.Settings.name)
                    }, userAccountInfoViewModel = userAccountInfoViewModel
                )
            }
            composable(ServixScreens.ProviderAccountInfo.name) {
                val providerAccountInfoViewModel: ProviderAccountInfoViewModel = hiltViewModel()
//                val serviceViewModel: ServiceViewModel = hiltViewModel()
//                serviceViewModel.getAllWorks()
                providerAccountInfoViewModel.getAllWorks()
                Log.d("ServixApp", "ServixApp: ${providerAccountInfoViewModel.getAllWork}")

                ProviderAccountInfoScreen(
                    modifier = Modifier.padding(innerPadding),
                    onAccountInfoDetailsClick = {
                        navController.navigate(ServixScreens.ProviderAccountInfoDetails.name)
                    },
                    onAddWorkToProfileItemOpenPhoto = { pid,pname ->
                        val uriEncode = Uri.encode(pname)
                        Log.d("ServixApp onAddWorkToProfileItemOpenPhoto", "ServixApp: $pid")
                        Log.d("ServixApp onAddWorkToProfileItemOpenPhoto", "ServixApp: $pname")
                        Log.d("ServixApp onAddWorkToProfileItemOpenPhoto", "ServixApp: $uriEncode")
                        navController.navigate(ServixScreens.SeeWork.name + "/$pid" + "/$uriEncode")
                    },
                    onAddWorkToProfile = {
                        navController.navigate(ServixScreens.AddNeWorkToProfileItems.name)
                    },
                    getWorks = providerAccountInfoViewModel.getAllWork,
                    providerAccountInfoViewModel = providerAccountInfoViewModel
                )
            }
            composable(ServixScreens.ProviderAccountInfoDetails.name) {
                val providerAccountInfoViewModel: ProviderAccountInfoViewModel = hiltViewModel()

                ProviderAccountInfoDetailsScreen(
                    modifier = Modifier.padding(innerPadding),

                    onSaveChangesClick = {
                        providerAccountInfoViewModel.updateProviderData()
                        navController.navigate(ServixScreens.Settings.name)
                    },
                    onPhotoChangeClick = {},
                    providerAccountInfoViewModel = providerAccountInfoViewModel
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
                        Log.d("NAvFirstScreenOnForgotPasswordChange", "FirstScreenOnForgotPasswordChange: ${userViewModel.emailN}")
                        userViewModel.forgotPassword()
//                        navController.navigate(ServixScreens.Otp.name)
                        if (userViewModel.isDone){
                            navController.navigate(ServixScreens.ResetPassword.name)
                        }

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
                        userViewModel.resetPassword(intentUriData.lastPathSegment.toString())
                        navController.navigate(ServixScreens.AfterPassword.name) {
                            popUpTo(ServixScreens.ResetPassword.name) {
                                inclusive = true
                            }
                        }
                    },
                    userViewModel = userViewModel
                )
            }
            composable(ServixScreens.AfterPassword.name) {
                AfterPasswordChange(
                    onBackToLoginClick = {
                        navController.navigate(ServixScreens.Login.name) {
                            popUpTo(ServixScreens.ResetPassword.name) {
                                inclusive = true
                            }
                        }
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
                            navController.navigate(ServixScreens.Login.name)
                        }
                    }
                )
            }
            composable(ServixScreens.HomeUser.name) {
                val serviceViewmodel: ServiceViewModel = hiltViewModel()

                UserHomeScreen(
                    modifier = Modifier.padding(innerPadding),

                    onTextFieldClick = {
                        navController.navigate(ServixScreens.ShareProblemScreen.name)
                    },
                    onServiceItemClick = { id, serviceName ->
                        navController.navigate(ServixScreens.FindProvider.name + "/$id" + "/$serviceName")

                    },
                    serviceViewModel = serviceViewmodel

                )
            }
            composable(ServixScreens.Notification.name) {
//                val notificationViewModel:NotificationViewModel= hiltViewModel()

                Log.d(
                    "getAllNotifications",
                    "ServixApp: ${notificationViewModel.getAllNotifications()}"
                )
//                notificationViewModel.getAllNotifications()
//                notificationViewModel.getAllSpecificNotifications()
                Log.d(
                    "getAllNotifications",
                    "ServixApp: ${notificationViewModel.getAllNotifications}"
                )
                NotificationScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNotificationItemClick = {
//                                              navController.navigate(ServixScreens.AddNeWorkToProfileItems.name)
                            id,recipient1 ->
                        notificationViewModel.getPostById(id)
                        Log.d("getPostById", "ServixApp: ${notificationViewModel.getPostById(id)}")
                        Log.d("getPostById", "ServixApp: ${id}")
                        Log.d("getPostById", "ServixApp: ${notificationViewModel.getPostById}")
//                        id->
                        navController.navigate(ServixScreens.NotifiPostItemDetailByID.name + "/$id" + "/$recipient1")
                    },
                    allNotification = notificationViewModel.getAllNotifications
                )
            }

            composable(ServixScreens.ShareProblemScreen.name) {
                val serviceViewmodel: ServiceViewModel = hiltViewModel()

                ShareProblemScreen(
                    onCancelClick = { navController.popBackStack() },
                    onShareClick = { navController.navigate(ServixScreens.HomeUser.name) },
                    serviceViewModel = serviceViewmodel
                )
            }
            composable(ServixScreens.NotifiPostItemDetailByID.name + "/{id}" + "/{recipient1}", arguments =
            listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("recipient1") { type = NavType.IntType }

            )
            ) { backStackEntry ->
//                val notificationViewModel:NotificationViewModel= hiltViewModel()


                val recipient1 = backStackEntry.arguments?.getInt("recipient1")
                Log.d("NotificationDetails", "NotificationDetails: $recipient1")
                val itemId = backStackEntry.arguments?.getInt("id")
                val item = notificationViewModel.getPostById.find { it.id == itemId }
                Log.d("PostDetails", "ServixApp: itemId:  ${itemId} || item: ${item}||")
                var detials = GeneralPostAccept("")
                var show by remember { mutableStateOf(false) }
                if (item != null) {
                    NotificationDetails(
                        onNotifiPostItemDetailToOpenIt = { navController.popBackStack() },
                        getPostDataItem = item,
                        recipient1 = recipient1,
                        onAcceptButtonClick = {
                            coroutineScope.launch {
                                if (itemId != null) {
                                    notificationViewModel.acceptPost(itemId)
                                    Log.d(
                                        "acceptPost",
                                        "acceptPost: ${notificationViewModel.acceptPost}"
                                    )
                                    delay(1000)
                                    show = true
                                    navController.navigate(ServixScreens.Notification.name)
                                }
                            }
                        }
                    )
                    val message = notificationViewModel.acceptPost.collectAsState()
                    LaunchedEffect(key1 = show) {
                        if (show) {
                            Toast.makeText(MyApplication.getApplicationContext().applicationContext, message.value, Toast.LENGTH_SHORT).show()
                            show = false // Reset the show flag to prevent re-displaying the Toast
                        }
                    }
//                    if (show)
//                    Toast.makeText(MyApplication.getApplicationContext().applicationContext,x.value,Toast.LENGTH_SHORT).show()
                }
            }


            composable(ServixScreens.NewPasswordScreen.name) {
                val settingsViewModel: SettingsViewModel = hiltViewModel()

                NewPasswordScreen(
                    onSavePasswordChangeClick = {
                        Log.d(
                            "changePassword",
                            "ServixApp:  || ${settingsViewModel.oldPassword} || ${settingsViewModel.newPassword}  ||"
                        )
                        settingsViewModel.changePassword(
                            NewOldPassword(
                                settingsViewModel.oldPassword,
                                settingsViewModel.newPassword
                            )
                        )
                        Log.d("userTypeFlow.value ", "ServixApp: ${userTypeFlow.value} ")
                        if (userTypeFlow.value == UserType.OwnerPerson.name) {
                            navController.navigate(ServixScreens.HomeUser.name)
                        } else {
                            navController.navigate(ServixScreens.HomeProvider.name)
                        }
//                        navController.navigate(ServixScreens.Home.name)
                    },
                    vm = settingsViewModel
                )
            }
            composable(ServixScreens.AddNeWorkToProfileItems.name) {
                val providerAccountInfoViewModel: ProviderAccountInfoViewModel = hiltViewModel()

//                serviceViewModel.getAllWorks()
                AddNeWorkToProfileItems(
                    onAddWorkToProfileItemOpenPhoto = {},
                    providerAccountInfoViewModel = providerAccountInfoViewModel,
                    getWorks = providerAccountInfoViewModel.getAllWork,
                    onSaveWorkClick = {
                        providerAccountInfoViewModel.addWork()
                        navController.navigate(ServixScreens.ProviderAccountInfo.name)
                    },
//                    onDeleteIconClick = serviceViewModel.deleteWork()
                )
            }

            composable(
                ServixScreens.SeeWork.name + "/{pid}" + "/{pname}",
                arguments = listOf(
                    navArgument("pid") { type = NavType.IntType },
                    navArgument("pname") { type = NavType.StringType },
                )
            ) {
                val seeWorkViewModel: SeeWorkViewModel = hiltViewModel()
//                val serviceViewModel: ServiceViewModel = hiltViewModel()
                val providerAccountInfoViewModel: ProviderAccountInfoViewModel = hiltViewModel()
                SeeWork(
                    onBackIconClick = { navController.popBackStack() },
                    seeWorkViewModel = seeWorkViewModel,
//                    image = providerAccountInfoViewModel.imageWork,
                    onDeleteIconClick = {
//                        Log.d("ImageID", "ServixApp: ${providerAccountInfoViewModel.imageId}")
                        providerAccountInfoViewModel.deleteWork(seeWorkViewModel.imageId)
                        navController.navigate(ServixScreens.ProviderAccountInfo.name)
                    },
//                    id =,
//                    serviceViewModel = serviceViewModel
                )
            }

            composable(ServixScreens.ShareProblemScreen.name) {
                val serviceViewmodel: ServiceViewModel = hiltViewModel()

                ShareProblemScreen(
                    onCancelClick = { navController.popBackStack() },
                    onShareClick = {
                        serviceViewmodel.shareCreatePost()
                        navController.navigate(ServixScreens.HomeUser.name)
                    },
                    serviceViewModel = serviceViewmodel
                )
            }



            composable(ServixScreens.HomeProvider.name) {
                val homeProviderHomeViewModel: ProviderHomeViewModel = hiltViewModel()
                if (userTypeFlow.value == UserType.HirePerson.name) {
                    homeProviderHomeViewModel.getPostsForProvider()

                    Log.d("getPostsForProvider", "ServixApp: ${userTypeFlow.value.toString()}")
                    //    Log.d("getPostsForProvider", "ServixApp: ${notificationViewModel.getPostsForProvider()}")
                    //    Log.d("getPostsForProvider", "ServixApp: ${notificationViewModel.getPostsForProvider}")
                    Log.d("getPostsForProvider", "here:")
                }
                ProviderPostScreen(
                    modifier = Modifier.padding(innerPadding),
                    onNotifiPostItemClick = { id ->
                        Log.d("PostDetailsNotifiPostItems", "ServixApp: id $id ")
                        navController.navigate(ServixScreens.PostDetails.name + "/$id")
                    },
                    viewModel = homeProviderHomeViewModel
                )

            }
            composable(ServixScreens.PostDetails.name + "/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val viewModel: PostDetailViewModel = hiltViewModel()
                val itemId = backStackEntry.arguments?.getInt("id")
                PostItemDetail(
                    onNotifiPostItemDetailToOpenIt = { /*TODO*/ },
                    viewModel = viewModel,
                    onAcceptButtonClickForSpecificProvider = {
                        viewModel.acceptPostForSpecificProvider(itemId!!)
                        navController.navigate(ServixScreens.HomeProvider.name)
                    },
                    onRejectButtonClickForSpecificProvider = {
                        viewModel.rejectPostForSpecificProvider(itemId!!)
                        navController.navigate(ServixScreens.HomeProvider.name)
                    }, modifier = Modifier.padding(innerPadding)
                )
            }
            composable(ServixScreens.ChatContactScreen.name) {
                val vm: ChatContactViewModel = hiltViewModel()
                ChatContactScreen(
                    modifier = Modifier.padding(innerPadding),
                    vm = vm,
                  onChatClick = {chatId,name,image,terminate_id,phone->
                        val uriEncode = Uri.encode(image)
                        val route = "ChatScreen/$chatId/$name/$uriEncode/$terminate_id/$phone"
                        Log.d("btx", "navigation problem url : ${ServixScreens.ChatScreen.name + "/$chatId"+"/$name"+"/$uriEncode"+"/$terminate_id"} "+"/$phone")
                        navController.navigate(route)

                    }
                )
            }
            composable(ServixScreens.ChatScreen.name+ "/{chatId}"+"/{name}"+"/{image}"+"/{terminate_id}"+"/{phone}", arguments = listOf(
                navArgument("chatId") {
                    type = NavType.IntType
                }, navArgument("name"){type= NavType.StringType}, navArgument("image"){type=
                    NavType.StringType}, navArgument("terminate_id"){type= NavType.IntType}
            )
            ) {
                val vm:ChatViewModel= hiltViewModel()
                var show by remember { mutableStateOf(false) }

                ChatScreen(vm,modifier=Modifier.padding(innerPadding),userType=userTypeFlow.value, onConfirmClick =
                    {
                        coroutineScope.launch{
                            show=true
                            delay(500)

                            navController.navigate(ServixScreens.ChatContactScreen.name)
                        }

                    })
                LaunchedEffect(key1 = show) {
                    if (show) {
                        Toast.makeText(MyApplication.getApplicationContext().applicationContext, "Done", Toast.LENGTH_SHORT).show()
                        show = false
                    }
                }
            }
            composable(ServixScreens.LoadingScreen.name){
                LoadingScreen()
            }

        }
    }
}