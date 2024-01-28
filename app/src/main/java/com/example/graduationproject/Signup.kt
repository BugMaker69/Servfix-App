package com.example.graduationproject

import android.content.res.Configuration
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.ui.theme.DarkBlue
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun Signup(
    modifier: Modifier = Modifier,
    onSignupClick: (String) -> Unit,
    onLoginClick: () -> Unit,
    userViewModel: UserViewModel = viewModel()
) {
    var press by remember { mutableStateOf(false) }
    var pressC by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(rememberScrollState()),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopCenter) {

            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }
        CustomTextField(
            fieldName = R.string.username,
            fieldValue = userViewModel.userName,
            onValueChange = { userViewModel.onUserNameChanged(it) },
            isValidField = { userViewModel.validateFullName(it) }
        )
        CustomTextField(
            fieldName = R.string.city,
            fieldValue = userViewModel.city,
            onValueChange = { userViewModel.onCityChanged(it) },
        )
        CustomTextField(
            fieldName = R.string.address,
            fieldValue = userViewModel.address,
            onValueChange = { userViewModel.onAddressChanged(it) },
        )
        CustomTextField(
            fieldName = R.string.email,
            fieldValue = userViewModel.emailN,
            onValueChange = { userViewModel.onEmailChangedN(it) },
            isValidField = { userViewModel.validateEmail(it) }
        )
        CustomTextField(
            fieldName = R.string.phone,
            fieldValue = userViewModel.phone,
            onValueChange = { userViewModel.onPhoneChanged(it) },
            isValidField = { userViewModel.validatePhoneNumber(it) }
        )
        CustomTextField(
            fieldName = R.string.password,
            fieldValue = userViewModel.passwordN,
            onValueChange = { userViewModel.onPasswordChangedN(it) },
            isValidField = { userViewModel.validatePassword(it) },
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
        CustomTextField(
            fieldName = R.string.conf_pass,
            fieldValue = userViewModel.passwordConf,
            onValueChange = { userViewModel.passwordConfChanged(it) },
            isValidField = {
                (userViewModel.passwordN == userViewModel.passwordConf)
                        && (userViewModel.validatePassword(userViewModel.passwordN))
            },
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
            text = R.string.next,
            fontSize = 20,
            onClick = { onSignupClick(userViewModel.phone) },
            indication = rememberRipple(),
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            shape = RoundedCornerShape(36.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 3.dp, color = Color.Gray)
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
