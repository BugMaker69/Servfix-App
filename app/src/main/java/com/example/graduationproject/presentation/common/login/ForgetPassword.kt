package com.example.graduationproject.presentation.common.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.signup.DisplayRequirements
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.ui.theme.DarkBlue
import kotlinx.coroutines.delay


@Composable
fun FirstScreenOnForgotPasswordChange(
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit,
    onLoginClick: () -> Unit,
    userViewModel: UserViewModel = viewModel()
) {

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomButtonAndText(
            text = R.string.forgot_password,
            contentColor = Color.Black,
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold,)
        )


        Box(contentAlignment = Alignment.TopCenter) {

            Image(
                painter = painterResource(id = R.drawable.forgot_password),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            )
        }

        CustomButtonAndText(
            text = R.string.enter_phone,
            contentColor = Color.Black
        )

        CustomTextField(
            modifier = Modifier,
            fieldName = R.string.phone,
            fieldValue = userViewModel.phoneChange,
            onValueChange = { userViewModel.onNewPhoneChanged(it)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )

        CustomButtonAndText(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = R.string.send_code,
            onClick = onSendClick,
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            indication = rememberRipple(),
            shape = RoundedCornerShape(36.dp)
        )

        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.remember_pass)
            CustomButtonAndText(
                text = R.string.login,
                onClick = onLoginClick,
                contentColor = Color.Blue
            )
        }

    }
}


@Composable
fun ResetPassword(
    modifier: Modifier = Modifier,
    onResetClick: () -> Unit,
    userViewModel: UserViewModel = viewModel()
) {

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var press by remember { mutableStateOf(false) }
        var pressC by remember { mutableStateOf(false) }

        val passwordFocusRequester = remember { FocusRequester() }
        val confirmPasswordFocusRequester = remember { FocusRequester() }


        CustomButtonAndText(
            text = R.string.reset_password,
            contentColor = Color.Black,
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold,)
        )


        Box(contentAlignment = Alignment.TopCenter) {

            Image(
                painter = painterResource(id = R.drawable.forgot_password),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
            )
        }


        CustomButtonAndText(
            text = R.string.enter_pass_rem,
            contentColor = Color.Black
        )

        CustomTextField(
            modifier = Modifier
                .focusRequester(passwordFocusRequester)
                .onFocusChanged { focusState ->
                    userViewModel.isNewPasswordFocused.value = focusState.isFocused
                }
            ,

            fieldName = R.string.new_password,
            fieldValue = userViewModel.newPassword,
            onValueChange = { userViewModel.onNewPasswordChanged(it) },
            isError = userViewModel.newPasswordNError,

            trailingIcon = {
                IconButton(onClick = { press = !press }) {
                    Icon(
                        imageVector = if (press) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
            },
            visualTransformation = if (press) PasswordVisualTransformation() else VisualTransformation.None
        )

        DisplayRequirements(
            isFieldFocused = userViewModel.isNewPasswordFocused.value,
            requirements = userViewModel.passwordRequirements,
            fieldValue = userViewModel.newPassword
        )

        CustomTextField(
            modifier = Modifier.focusRequester(confirmPasswordFocusRequester),

            fieldName = R.string.conf_pass,
            fieldValue = userViewModel.newPasswordConf,
            onValueChange = { userViewModel.newPasswordConfChanged(it) },
            isError = userViewModel.newPasswordConfError,

            trailingIcon = {
                IconButton(onClick = { pressC = !pressC }) {
                    Icon(
                        imageVector = if (pressC) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
            },
            visualTransformation = if (pressC) PasswordVisualTransformation() else VisualTransformation.None
        )

        CustomButtonAndText(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = R.string.reset_password,
            onClick = onResetClick,
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            indication = rememberRipple(),
            shape = RoundedCornerShape(36.dp)
        )
    }
}



@Composable
fun AfterPasswordChange(onBackToLoginClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Filled.CheckCircle,
                            contentDescription = "Success",
                            tint = DarkBlue,
                            modifier = Modifier.size(100.dp)
                        )

                        CustomButtonAndText(
                            text = R.string.congratulations,
                            contentColor = Color.Black,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        CustomButtonAndText(
                            text = R.string.password_changed_succ,
                            contentColor = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        CustomButtonAndText(
                            text = R.string.redirected,
                            contentColor = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            delay(3000L)
            showDialog = false
            onBackToLoginClick()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PhoneAgainPreview() {
    FirstScreenOnForgotPasswordChange(onSendClick =  {}, onLoginClick = {})
}


@Preview(showBackground = true)
@Composable
fun ResetPasswordPreview() {
    ResetPassword(onResetClick =  {})
}


@Preview(showBackground = true)
@Composable
fun AfterPasswordChangePreview() {
    AfterPasswordChange(onBackToLoginClick = {})
}


