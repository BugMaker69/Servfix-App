package com.example.graduationproject
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.ui.theme.DarkBlue

@Composable
fun SignupFirstScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onNextClick: (String) -> Unit,
) {
    val userViewModel: UserViewModel = viewModel()

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

            CustomTextField(
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

                )
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

            CustomButtonAndText(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = R.string.next,
                onClick = {
                    userViewModel.onNextFirstSignupClick()
                    if (userViewModel.userNameError) {
                        usernameFocusRequester.requestFocus()
                    } else if (userViewModel.cityError) {
                        cityFocusRequester.requestFocus()
                    } else if (userViewModel.addressError) {
                        addressFocusRequester.requestFocus()
                    } else if (userViewModel.phoneError) {
                        phoneNumberFocusRequester.requestFocus()
                    }
                    if(!userViewModel.userNameError && !userViewModel.cityError && !userViewModel.addressError && !userViewModel.phoneError  )
                        onNextClick(userViewModel.phone)
                },
                indication = rememberRipple(),
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(36.dp)
            )
        }
        Divider(thickness = 3.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.have_account)
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
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFinishClick: () -> Unit,
) {
    val userViewModel: UserViewModel = viewModel()

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
                        userViewModel.isEmailFocused.value = focusState.isFocused
                    },

                fieldName = R.string.email,
                fieldValue = userViewModel.emailN,
                onValueChange = { userViewModel.onEmailChangedN(it) },
                isError = userViewModel.emailNError,

                )
            DisplayRequirements(
                isFieldFocused = userViewModel.isEmailFocused.value,
                requirements = userViewModel.emailRequirements,
                fieldValue = userViewModel.emailN
            )

            CustomTextField(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
                    .onFocusChanged { focusState ->
                        userViewModel.isPasswordFocused.value = focusState.isFocused

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
                            imageVector = if (userViewModel.eyeIconPress) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "",
                            tint = Color.Black,
                        )
                    }
                },
                visualTransformation = if (userViewModel.eyeIconPress) PasswordVisualTransformation() else VisualTransformation.None
            )
            DisplayRequirements(
                isFieldFocused = userViewModel.isPasswordFocused.value,
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
                            imageVector = if (userViewModel.eyeIconPressC) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "",
                            tint = Color.Black,
                        )
                    }
                },
                visualTransformation = if (userViewModel.eyeIconPressC) PasswordVisualTransformation() else VisualTransformation.None
            )

        }


        Divider(thickness = 3.dp, color = Color.Gray)
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
                text = R.string.finish,
                onClick = {
                    Log.d("WHYYYYY", "SignupSecondScreen: ERRORR1")
                    userViewModel.onNextSecondSignupClick()
                    if (userViewModel.emailNError) {
                        Log.d("WHYYYYY", "SignupSecondScreen: ERRORR2")

                        emailFocusRequester.requestFocus()
                    } else if (userViewModel.passwordNError) {
                        passwordFocusRequester.requestFocus()
                    } else if (userViewModel.passwordConfError) {
                        confirmPasswordFocusRequester.requestFocus()
                    }
                    if(!userViewModel.emailNError && !userViewModel.passwordNError && !userViewModel.passwordConfError)
                        onFinishClick()
                    Log.d("WHYYYYY", "SignupSecondScreen: ERRORR3")

                },
                contentColor = Color.White,
                backgroundColor = DarkBlue,

                )
        }
    }
}