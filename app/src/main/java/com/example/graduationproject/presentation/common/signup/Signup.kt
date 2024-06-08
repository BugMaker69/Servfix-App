package com.example.graduationproject.presentation.common.signup

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.presentation.common.UserTypeViewModel
import com.example.graduationproject.ui.theme.DarkBlue

@Composable
fun SignupFirstScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onNextClick: () -> Unit,
    viewModel: UserTypeViewModel,

    ) {

    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()

    val usernameFocusRequester = remember { FocusRequester() }
    val cityFocusRequester = remember { FocusRequester() }
    val addressFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {

            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier
                    .focusRequester(usernameFocusRequester)
                    .onFocusChanged { focusState ->
                        userViewModel.isUsernameFocused.value = focusState.isFocused
                    },
                fieldName = R.string.username,
                fieldValue = userViewModel.userName,
                onValueChange = { userViewModel.onUserNameChanged(it) },
                isError = userViewModel.userNameError,
            )
            DisplayRequirements(
                isFieldFocused = userViewModel.isUsernameFocused.value,
                requirements = userViewModel.usernameRequirements,
                fieldValue = userViewModel.userName
            )

            CustomTextField(
                modifier = Modifier
                    .focusRequester(phoneNumberFocusRequester)
                    .onFocusChanged { focusState ->
                        userViewModel.isPhoneNumberFocused.value = focusState.isFocused
                    },

                fieldName = R.string.phone,
                fieldValue = userViewModel.phone,
                onValueChange = { userViewModel.onPhoneChanged(it) },
                isError = userViewModel.phoneError,

                )

            DisplayRequirements(
                isFieldFocused = userViewModel.isPhoneNumberFocused.value,
                requirements = userViewModel.phoneNumberRequirements,
                fieldValue = userViewModel.phone
            )

            CustomTextField(
                modifier = Modifier.focusRequester(addressFocusRequester),

                fieldName = R.string.address,
                fieldValue = userViewModel.address,
                onValueChange = { userViewModel.onAddressChanged(it) },
                isError = userViewModel.addressError,

                )

            /*        CustomTextField(
                        modifier = Modifier
                            .focusRequester(cityFocusRequester)
                            .onGloballyPositioned { coordinates ->
                                userViewModel.textfieldSize = coordinates.size.toSize()
                            },

                        fieldName = R.string.city,
                        fieldValue = userViewModel.selectedCityValue,
                        onValueChange = {},
                        readOnly = true,
                        isError = userViewModel.cityError,

                        trailingIcon = {
                            IconButton(onClick = { userViewModel.expanded = !userViewModel.expanded }) {
                                Icon(
                                    imageVector = if (userViewModel.expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                    contentDescription = "",
                                    tint = Color.Black,
                                )
                            }
                        },

                        )*/

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { userViewModel.expanded = !userViewModel.expanded }
                    .padding(top = 8.dp, bottom = 16.dp)
                    .defaultMinSize(
                        minWidth = OutlinedTextFieldDefaults.MinWidth,
                        minHeight = OutlinedTextFieldDefaults.MinHeight
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = OutlinedTextFieldDefaults.shape
                    )
                    .onGloballyPositioned { coordinates ->
                        userViewModel.textfieldSize = coordinates.size.toSize()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (userViewModel.selectedCityValue.isNotEmpty()) userViewModel.selectedCityValue else "City",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(7f)
                )

                Icon(
                    imageVector = if (userViewModel.expanded) Icons.Filled.ArrowDropUp
                    else Icons.Filled.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }


            DropdownMenu(
                expanded = userViewModel.expanded,
                onDismissRequest = { userViewModel.expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { userViewModel.textfieldSize.width.toDp() })

            ) {
                governorates.forEachIndexed { index, governorate ->
                    DropdownMenuItem(
                        onClick = {
                            userViewModel.selectedCityIndex = index
                            userViewModel.selectedCityValue = governorate
                            userViewModel.expanded = false
                            userViewModel.cityError = false
                        },
                        text = {
                            Text(text = governorate)
                        }
                    )
                }
            }
            if (userViewModel.cityError){
                Text(text = "Choose City First", color = Color.Red,modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), textAlign = TextAlign.Start)
            }

            CustomButtonAndText(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = R.string.next,
                onClick = {
                    Log.d("WHYYYYY", "SignupSecondScreen: USER TYPE ${viewModel.userType.value}")

                    userViewModel.onNextFirstSignupClick()
                    if (userViewModel.userNameError) {
                        usernameFocusRequester.requestFocus()
                    } else if (userViewModel.cityError) {
//                        cityFocusRequester.requestFocus()
                    } else if (userViewModel.addressError) {
                        addressFocusRequester.requestFocus()
                    } else if (userViewModel.phoneError) {
                        phoneNumberFocusRequester.requestFocus()
                    }
                    if (!userViewModel.userNameError && !userViewModel.cityError && !userViewModel.addressError && !userViewModel.phoneError) {

                        onNextClick()

                    }
                },
                indication = rememberRipple(),
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(36.dp)
            )
        }
        Divider(thickness = 3.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier=Modifier.padding(bottom = 16.dp),verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.have_account, contentColor = Color.Gray)
            CustomButtonAndText(
                text = R.string.login,
                onClick = onLoginClick,
                contentColor = Color.Blue
            )
        }
    }
}


@Composable
fun SignupSecondScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFinishClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: UserTypeViewModel,

    ) {
    val context = LocalContext.current

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            CustomTextField(
                modifier = Modifier
                    .focusRequester(emailFocusRequester)
                    .onFocusChanged { focusState ->
                        userViewModel.isEmailNFocused.value = focusState.isFocused
                    },

                fieldName = R.string.email,
                fieldValue = userViewModel.emailN,
                onValueChange = { userViewModel.onEmailChangedN(it) },
                isError = userViewModel.emailNError,

                )
            DisplayRequirements(
                isFieldFocused = userViewModel.isEmailNFocused.value,
                requirements = userViewModel.emailRequirements,
                fieldValue = userViewModel.emailN
            )

            CustomTextField(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
                    .onFocusChanged { focusState ->
                        userViewModel.isPasswordNFocused.value = focusState.isFocused

                    },

                fieldName = R.string.password,
                fieldValue = userViewModel.passwordN,
                onValueChange = { userViewModel.onPasswordChangedN(it) },
                isError = userViewModel.passwordNError,

                trailingIcon = {
                    IconButton(onClick = {
                        userViewModel.eyeIconPress = !userViewModel.eyeIconPress
                    }) {
                        Icon(
                            imageVector = if (!userViewModel.eyeIconPress) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "",
                            tint = Color.Black,
                        )
                    }
                },
                visualTransformation = if (!userViewModel.eyeIconPress) PasswordVisualTransformation() else VisualTransformation.None
            )
            DisplayRequirements(
                isFieldFocused = userViewModel.isPasswordNFocused.value,
                requirements = userViewModel.passwordRequirements,
                fieldValue = userViewModel.passwordN
            )

            CustomTextField(
                modifier = Modifier.focusRequester(confirmPasswordFocusRequester),

                fieldName = R.string.conf_pass,
                fieldValue = userViewModel.passwordConf,
                onValueChange = { userViewModel.passwordConfChanged(it) },
                isError = userViewModel.passwordConfError,

                trailingIcon = {
                    IconButton(onClick = {
                        userViewModel.eyeIconPressC = !userViewModel.eyeIconPressC
                    }) {
                        Icon(
                            imageVector = if (!userViewModel.eyeIconPressC) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "",
                            tint = Color.Black,
                        )
                    }
                },
                visualTransformation = if (!userViewModel.eyeIconPressC) PasswordVisualTransformation() else VisualTransformation.None
            )

        }


//        Divider(thickness = 3.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButtonAndText(
                modifier = Modifier.fillMaxWidth(.25f),
                text = R.string.back,
                onClick = onBackClick,
                contentColor = DarkBlue,
                backgroundColor = Color.LightGray
            )
            CustomButtonAndText(
                modifier = Modifier.fillMaxWidth(.75f),
                text = if (viewModel.userType.value == UserType.OwnerPerson) R.string.finish else R.string.next,
                onClick = {
                    Log.d("WHYYYYY", "SignupSecondScreen: ERRORR1")
                    Log.d("WHYYYYY", "SignupSecondScreen: USER TYPE ${viewModel.userType.value}")
                    if (viewModel.userType.value == UserType.OwnerPerson) {
                        userViewModel.onNextSecondSignupClick()
                        userViewModel.onFinishSignupClick()
                    } else
                        userViewModel.onNextSecondSignupClick()
                    if (userViewModel.emailNError) {
                        Log.d("WHYYYYY", "SignupSecondScreen: ERRORR2")

                        emailFocusRequester.requestFocus()
                    } else if (userViewModel.passwordNError) {
                        passwordFocusRequester.requestFocus()
                    } else if (userViewModel.passwordConfError) {
                        confirmPasswordFocusRequester.requestFocus()
                    }
                    if (!userViewModel.emailNError && !userViewModel.passwordNError && !userViewModel.passwordConfError) {
                        if (viewModel.userType.value == UserType.OwnerPerson) {
                            userViewModel.sendVerificationCode(
                                activity = context as Activity,
                                callbacks = userViewModel.callbacks
                            )
                        }
                        Log.d("boolo", "registerUser: ha?")
                        onFinishClick()
                    }


                    Log.d("WHYYYYY", "SignupSecondScreen: ERRORR3")

                },
                contentColor = Color.White,
                backgroundColor = DarkBlue,

                )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 3.dp, color = Color.Gray)
        Row(modifier=Modifier.padding(bottom = 16.dp),verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.have_account, contentColor = Color.Gray)
            CustomButtonAndText(
                text = R.string.login,
                contentColor = Color.Blue,
                onClick = onLoginClick
            )
        }


    }
}


@Composable
fun SignupThirdScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onFinishClick: () -> Unit,
    onBackClick: () -> Unit,
    userViewModel: UserViewModel

) {

    val context = LocalContext.current
    val services = context.resources.getStringArray(R.array.services).toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }

        /*        val galleryLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                        uri?.let {
                            userViewModel.handleGalleryResultForIdImage(context, uri)
                        }
                    }

                val galleryLauncherForWork =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                        uri?.let {
                            userViewModel.handleGalleryResultForWork(context, uri)
                        }
                    }*/

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

// Service
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { userViewModel.expandedService = !userViewModel.expandedService }
                    .padding(8.dp)
                    .defaultMinSize(
                        minWidth = OutlinedTextFieldDefaults.MinWidth,
                        minHeight = OutlinedTextFieldDefaults.MinHeight
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = OutlinedTextFieldDefaults.shape
                    )
                    .onGloballyPositioned { coordinates ->
                        userViewModel.textfieldServiceSize = coordinates.size.toSize()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = if (userViewModel.selectedServiceValue.isNotEmpty()) userViewModel.selectedServiceValue else "Your Service",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(7f)
                )

                Icon(
                    imageVector = if (userViewModel.expandedService) Icons.Filled.ArrowDropUp
                    else Icons.Filled.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            DropdownMenu(
                expanded = userViewModel.expandedService,
                onDismissRequest = { userViewModel.expandedService = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { userViewModel.textfieldServiceSize.width.toDp() })

            ) {
                services.forEachIndexed { index, service ->
                    DropdownMenuItem(
                        onClick = {
                            userViewModel.selectedServiceIndex = index
                            userViewModel.selectedServiceValue = service
                            userViewModel.expandedService = false
                            userViewModel.serviceError = false
                        },
                        text = {
                            Text(text = service)
                        }
                    )
                }
            }
            if (userViewModel.serviceError){
                Text(text = "Choose Service First", color = Color.Red,modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), textAlign = TextAlign.Start)
            }


//Fixed Salary
            CustomTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                fieldName = R.string.fixed_salary,
                fieldValue = userViewModel.fixedSalary,
                onValueChange = { userViewModel.onFixedSalaryChanged(it) },
            )
            if (userViewModel.fixedSalaryError){
                Text(text = "Type Salary First", color = Color.Red,modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), textAlign = TextAlign.Start)
            }

// National ID
            val launcher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
                    userViewModel.imageUri = uri
                }

/*            val launcher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                    userViewModel.imageUri = uri
                }*/

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable(
//                        enabled = !userViewModel.isLoading.value,
                    ) {
//                        userViewModel.startLoading()
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                        launcher.launch("image/*")
//                        userViewModel.stopLoading()

                    }
                    .padding(8.dp)
                    .sizeIn(
                        minWidth = OutlinedTextFieldDefaults.MinWidth,
                        minHeight = OutlinedTextFieldDefaults.MinHeight,
                        maxWidth = 150.dp,
                        maxHeight = 150.dp,
                    )
//                    .defaultMinSize(
//                        minWidth = OutlinedTextFieldDefaults.MinWidth,
//                        minHeight = OutlinedTextFieldDefaults.MinHeight
//                    )
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = OutlinedTextFieldDefaults.shape
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (userViewModel.isLoading.value) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                }
                if (userViewModel.imageUri == null) {
                    Text(
                        text = "National ID",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(7f)
                    )
                } else {
                    userViewModel.showText = false
                    Image(
                        painter = rememberImagePainter(data = userViewModel.imageUri)!!,
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(16.dp)
                            .weight(7f)
                    )
                }
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            if (userViewModel.showText) {
                Text(text = "Error Its Required", color = Color.Red,modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), textAlign = TextAlign.Start)
            }

        }


        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButtonAndText(
                    modifier = Modifier.fillMaxWidth(.25f),
                    text = R.string.back,
                    onClick = onBackClick,
                    contentColor = DarkBlue,
                    backgroundColor = Color.LightGray
                )

                CustomButtonAndText(
                    Modifier.fillMaxWidth(.75f),
                    text = R.string.finish,
                    indication = rememberRipple(),
                    onClick = {
                        userViewModel.thirdSignUpFinish()
                        if (userViewModel.idImageFileError) {
                            userViewModel.showText = true
                        }
                        else {
                            userViewModel.providerRegister()
                            userViewModel.onFinishSignupClick()
                            userViewModel.sendVerificationCode(
                                activity = context as Activity,
                                callbacks = userViewModel.callbacks
                            )
                            onFinishClick()
                        }
                    },
                    backgroundColor = DarkBlue,
                    contentColor = Color.White,
//                    shape = RoundedCornerShape(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 3.dp, color = Color.Gray)
            Row(modifier=Modifier.padding(bottom = 16.dp),verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                CustomButtonAndText(text = R.string.have_account, contentColor = Color.Gray)
                CustomButtonAndText(
                    text = R.string.login,
                    contentColor = Color.Blue,
                  onClick = onLoginClick
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SignupThirdScreenPreview() {
    SignupThirdScreen(Modifier, {}, {}, {}, viewModel())
}