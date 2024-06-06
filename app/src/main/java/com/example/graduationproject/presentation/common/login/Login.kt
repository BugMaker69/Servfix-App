package com.example.graduationproject.presentation.common.login

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.ui.theme.Black


@SuppressLint("SuspiciousIndentation")
@Composable
fun Login(
    modifier: Modifier,
    onForgetPasswordClick: () -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    var press by remember { mutableStateOf(false) }
    userViewModel.isPasswordForget = false
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (isLandscape) {
            Box(contentAlignment = Alignment.TopCenter) {
                Image(
                    modifier = Modifier.size(150.dp).clip(RoundedCornerShape(8.dp)),
//                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.servix_frame),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                )
            }
        } else {
            Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.servix_frame),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                )
            }
        }

        /*        Box(contentAlignment = Alignment.TopCenter) {
                    Image(
                        painter = painterResource(id = R.drawable.servix_frame),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                    )
                }*/


        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            CustomTextField(
                fieldName = R.string.username,
                fieldValue = userViewModel.userNameLogin,
                onValueChange = { userViewModel.onuserNameChanged(it) },
                isError = userViewModel.userNameError
            )
            CustomTextField(
                fieldName = R.string.password,
                fieldValue = userViewModel.password,
                onValueChange = { userViewModel.onPasswordChanged(it) },
                isError = userViewModel.passwordError,
                trailingIcon = {
                    IconButton(onClick = { press = !press }) {
                        Icon(
                            imageVector = if (!press) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "",
                            tint = Black,
                        )
                    }
                },
                visualTransformation = if (!press) PasswordVisualTransformation() else VisualTransformation.None
            )

            CustomButtonAndText(
                text = R.string.forgot_password,
                onClick = onForgetPasswordClick,
                alignment = Alignment.CenterEnd,
                contentColor = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            if (userViewModel.error != null) {
                LaunchedEffect(userViewModel.error) {
                    Toast.makeText(context, "${userViewModel.error}", Toast.LENGTH_SHORT).show()
                    userViewModel.error = null
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
                shape = RoundedCornerShape(36.dp),
                enabled = userViewModel.loginEnabled,
                onClick = {
                    if (userViewModel.userNameLogin.isNotEmpty() && userViewModel.password.isNotEmpty())
                        onLoginClick()
                }) {

                Text(text = "Login")
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 3.dp, color = Color.Gray)
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            CustomButtonAndText(text = R.string.have_not_account, contentColor = Color.Gray)
            CustomButtonAndText(
                text = R.string.signup,
                contentColor = Color.Blue,
                onClick = onSignupClick
            )
        }
    }

}


/*



@Composable
fun Login(
    modifier: Modifier = Modifier,
    onForgetPasswordClick: () -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    userViewModel: UserViewModel
) {
    var press by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    userViewModel.isPasswordForget = false
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            if (isLandscape) {
                Image(
                    painter = painterResource(id = R.drawable.servix_frame),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(300.dp),
                    alignment = Alignment.TopCenter,
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.servix_frame),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(),
                    alignment = Alignment.TopCenter,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            CustomTextField(
                fieldName = R.string.username,
                fieldValue = userViewModel.email,
                onValueChange = { userViewModel.onEmailChanged(it) },
                isError = userViewModel.emailError
            )
            CustomTextField(
                fieldName = R.string.password,
                fieldValue = userViewModel.password,
                onValueChange = { userViewModel.onPasswordChanged(it) },
                isError = userViewModel.passwordError,
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

            CustomButtonAndText(
                text = R.string.forgot_password,
                onClick = onForgetPasswordClick,
                alignment = Alignment.CenterEnd,
                contentColor = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(64.dp))

        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            CustomButtonAndText(
                Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                text = R.string.login,
                indication = rememberRipple(),
                onClick = {
                    userViewModel.login()
                    onLoginClick()
                          },
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(36.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 3.dp, color = Color.Gray)
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                CustomButtonAndText(text = R.string.have_not_account, contentColor = Color.Gray)
                CustomButtonAndText(
                    text = R.string.signup,
                    contentColor = Color.Blue,
                    onClick = onSignupClick
                )
            }
        }
    }
}

*/


/*
@Composable
fun Login(
    modifier: Modifier = Modifier,
    onForgetPasswordClick: () -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    userViewModel: UserViewModel
) {
    var press by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    userViewModel.isPasswordForget = false

    LazyColumn(
        modifier = Modifier
            .fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Box(contentAlignment = Alignment.TopCenter) {
                Image(
                    painter = painterResource(id = R.drawable.servix_frame),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(300.dp),
                    alignment = Alignment.TopCenter,
                )
            }
        }

        item {
            CustomTextField(
                fieldName = R.string.username,
                fieldValue = userViewModel.email,
                onValueChange = { userViewModel.onEmailChanged(it) },
                isError = userViewModel.emailError
            )
            CustomTextField(
                fieldName = R.string.password,
                fieldValue = userViewModel.password,
                onValueChange = { userViewModel.onPasswordChanged(it) },
                isError = userViewModel.passwordError,
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

            CustomButtonAndText(
                text = R.string.forgot_password,
                onClick = onForgetPasswordClick,
                alignment = Alignment.CenterEnd,
                contentColor = Color.Gray,
//                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(64.dp))

        }

        item {
            CustomButtonAndText(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = R.string.login,
                indication = rememberRipple(),
                onClick = {
                    userViewModel.login()
                    onLoginClick()
                },
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(36.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 3.dp, color = Color.Gray)
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                CustomButtonAndText(text = R.string.have_not_account, contentColor = Color.Gray)
                CustomButtonAndText(
                    text = R.string.signup,
                    contentColor = Color.Blue,
                    onClick = onSignupClick
                )
            }
        }
    }
}
*/
