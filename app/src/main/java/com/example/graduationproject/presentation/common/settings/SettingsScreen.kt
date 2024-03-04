package com.example.graduationproject.presentation.common.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.LockPerson
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonRemove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.ui.BottomAppBar
import com.example.graduationproject.R
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfo
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoDetails
import com.example.graduationproject.presentation.accountinfo.UserAccountInfo
import com.example.graduationproject.presentation.accountinfo.UserAccountInfoDetails
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.presentation.common.CustomTopAppBar
import com.example.graduationproject.ui.theme.DarkBlue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onAccountInfoClick: () -> Unit,
    onDeleteMyAccountClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onBackButtonOnTopNavBar: () -> Unit,
    onBottomNavigationItemClick: (String) -> Unit,
) {
    Scaffold(
        topBar = { SettingsTopBar(onBackButtonOnTopNavBar = onBackButtonOnTopNavBar) },
        bottomBar = { BottomAppBar(onBottomNavigationItemClick = onBottomNavigationItemClick) }
    ) {
        SettingsScreenContent(
            modifier.padding(top = it.calculateTopPadding()),
            onAccountInfoClick = onAccountInfoClick,
            onDeleteMyAccountClick = onDeleteMyAccountClick,
            onSecurityClick = onSecurityClick,
            onSignOutClick = onSignOutClick
        )
    }
}


@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    onAccountInfoClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onDeleteMyAccountClick: () -> Unit,
    onSignOutClick: () -> Unit,
) {

    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onAccountInfoClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "Account Info", modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onSecurityClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.LockPerson,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "Security", modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onDeleteMyAccountClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonRemove,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "Delete My Account", modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), thickness = 2.dp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onSignOutClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "Sign Out",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 1.sp,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            )
        }
    }
}


@Composable
fun DeleteAccountDialog(
    modifier: Modifier = Modifier,
    onDeleteAccountConfirmButtonClick: () -> Unit,
    onDeleteAccountDismissButtonClick: () -> Unit,
) {
    CustomDialog(
        confirmButtonText = "Delete",
        dismissButtonText = "Cancel",
        dialogTitle = "Confirmation",
        dialogText = "Are you sure you want to Delete Account?",
        onConfirmButtonClick = onDeleteAccountConfirmButtonClick,
        onDismissButtonClick = onDeleteAccountDismissButtonClick
    )
}


@Composable
fun DeleteAccountScreen(
    modifier: Modifier = Modifier,
    onDeleteAccountConfirmClick: () -> Unit,
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        ) {

        Text(text = "Deleting Your Account", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Text(
            text = "we hate to see you go , but if you are decided then here are a few things you need to know",
            color = Color.Black.copy(alpha = .7f),
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "once your account is deleted ,you can’t reactivate it, recover any data , or regain access . you will need to set up a new account if you want to use Servfix again",
            color = Color.Black.copy(alpha = .7f),
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)

        )

        Button(
            onClick = onDeleteAccountConfirmClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Delete")
        }

    }


}

@Composable
fun NewPasswordScreen(
    modifier: Modifier = Modifier,
    onSavePasswordChangeClick: () -> Unit
) {

    var eyeIconPress by remember { mutableStateOf(false) }
    var eyeIconPressConf by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        Text(
            text = "New Password",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "Your password must be at least 8 characters long, and contain at least one digit , One capital letter and One special character",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
        )

        Text(
            text = "Password",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Password") },
            trailingIcon = {
                IconButton(onClick = {
                    eyeIconPress = !eyeIconPress
                }) {
                    Icon(
                        imageVector = if (eyeIconPress) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
            },
            visualTransformation = if (eyeIconPress) PasswordVisualTransformation() else VisualTransformation.None
        )

        Text(
            text = "Confirm Password",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Confirm Password") },
            trailingIcon = {
                IconButton(onClick = {
                    eyeIconPressConf = !eyeIconPressConf
                }) {
                    Icon(
                        imageVector = if (eyeIconPressConf) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
            },
            visualTransformation = if (eyeIconPressConf) PasswordVisualTransformation() else VisualTransformation.None
        )

        CustomButtonAndText(
            text = R.string.done,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            onClick = onSavePasswordChangeClick
        )

    }

}


@Composable
fun SignOutDialog(
    modifier: Modifier = Modifier,
    onSignOutConfirmButtonClick: () -> Unit,
    onSignOutDismissButtonClick: () -> Unit,
) {

    CustomDialog(
        confirmButtonText = "Sign Out",
        dismissButtonText = "Cancel",
        dialogTitle = "Confirmation",
        dialogText = "Are you sure you want to sign out?",
        onConfirmButtonClick = onSignOutConfirmButtonClick,
        onDismissButtonClick = onSignOutDismissButtonClick
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit
) {
    CustomTopAppBar(
        title = "Settings",
        navigationIcon = {
            IconButton(onClick = onBackButtonOnTopNavBar) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
            }
        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen()
//}


@Preview(showBackground = true)
@Composable
fun ProviderAccountInfoDetailsPreview() {
    ProviderAccountInfoDetails(Modifier, {}, {})
}

@Preview(showBackground = true)
@Composable
fun ProviderAccountInfoPreview() {
    ProviderAccountInfo(Modifier, {})
}

@Preview(showBackground = true)
@Composable
fun NewPasswordScreenPreview() {
    NewPasswordScreen(Modifier, {})
}

@Preview(showBackground = true)
@Composable
fun UserAccountInfoDetailsPreview() {
    UserAccountInfoDetails(Modifier, {}, {})
}

@Preview(showBackground = true)
@Composable
fun UserAccountInfoPreview() {
    UserAccountInfo(Modifier, {})
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountScreenPreview() {
    DeleteAccountScreen(Modifier, {})
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountDialogPreview() {
    DeleteAccountDialog(Modifier, {}, {})
}

@Preview(showBackground = true)
@Composable
fun SignOutDialogPreview() {
    SignOutDialog(Modifier, {}, {})
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenContentPreview() {
    SettingsScreenContent(Modifier, {}, {}, {}, {})
}


@Preview(showBackground = true)
@Composable
fun SettingsTopBarPreview() {
    SettingsTopBar(Modifier, {})
}