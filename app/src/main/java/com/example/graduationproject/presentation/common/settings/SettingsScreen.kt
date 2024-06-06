package com.example.graduationproject.presentation.common.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.presentation.common.CustomTopAppBar
import com.example.graduationproject.ui.theme.DarkBlue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onAccountInfoClick: () -> Unit,
    onDeleteMyAccountClick: (String) -> Unit,
    onSecurityClick: () -> Unit,
    onSignOutClick: () -> Unit,
    settingsViewModel: SettingsViewModel
) {

    SettingsScreenContent(
        modifier.padding(top = innerPadding.calculateTopPadding()),
        onAccountInfoClick = onAccountInfoClick,
        onDeleteMyAccountClick = { pass ->
            onDeleteMyAccountClick(pass)
        },
        onSecurityClick = onSecurityClick,
        onSignOutClick = onSignOutClick,
        vm = settingsViewModel
    )

}


@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    onAccountInfoClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onDeleteMyAccountClick: (String) -> Unit,
    onSignOutClick: () -> Unit,
    vm: SettingsViewModel
) {
    //  TODO Place This Values In ViewModel
    var showSignOutDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

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
                text = stringResource(id = R.string.AccountInfo), modifier = Modifier
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
                text = stringResource(id =R.string.security), modifier = Modifier
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
                .clickable { showDeleteDialog = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showDeleteDialog) {
                DeleteAccountDialog(vm = vm, onDeleteAccountConfirmButtonClick = {
                    //  TODO We Need To Check First If Password He Enters Is Correct
                    Log.d("SignOUT", "SettingsScreenContent: SignOUT")
                    onDeleteMyAccountClick(vm.confirmedPassword)
                    showDeleteDialog = false
                },
                    onDeleteAccountDismissButtonClick = { showDeleteDialog = false }
                )
            }
            Icon(
                imageVector = Icons.Outlined.PersonRemove,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.deleteAccount), modifier = Modifier
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
                .clickable { showSignOutDialog = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //  TODO Place This Values In ViewModel
            if (showSignOutDialog) {
                SignOutDialog(onSignOutConfirmButtonClick = {
                    Log.d("SignOUT", "SettingsScreenContent: SignOUT")
                    showSignOutDialog = false
                    onSignOutClick()
                },
                    onSignOutDismissButtonClick = { showSignOutDialog = false })
            }

            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.signOut),
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
    vm: SettingsViewModel,
    modifier: Modifier = Modifier,
    onDeleteAccountConfirmButtonClick: () -> Unit,
    onDeleteAccountDismissButtonClick: () -> Unit,
) {
    CustomDialog(
        confirmButtonText = "Delete",
        dismissButtonText = "Cancel",
        title = {
            Text(
                text = "Confirmation",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                Text(
                    text = "Are you sure you want to Delete Account?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 4.dp),
                )
                Text(
                    text = "Enter Your Password to Confirm Your Action",
                    modifier = Modifier.padding(vertical = 4.dp),
                )
                OutlinedTextField(
                    value = vm.confirmedPassword,
                    onValueChange = { vm.confirmedPasswordChange(it) },
                )
            }
        },
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
fun SecurityScreen(
    modifier: Modifier = Modifier,
    onDonePasswordClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        var eyeIconPress by remember { mutableStateOf(false) }

        Text(
            text = "Security",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(128.dp))

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

        Spacer(modifier = Modifier.height(128.dp))

        Button(
            onClick = onDonePasswordClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Done")
        }

    }

}


@Composable
fun NewPasswordScreen(
    modifier: Modifier = Modifier,
    onSavePasswordChangeClick: () -> Unit,
    vm: SettingsViewModel

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
            text = "Old Password",
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
            value = vm.oldPassword,
            onValueChange = { vm.onOldPasswordChange(it) },
            placeholder = { Text(text = "Old Password") },
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
            text = "New Password",
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
            value = vm.newPassword,
            onValueChange = { vm.onNewPasswordChange(it) },
            placeholder = { Text(text = "New Password") },
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
        title = {
            Text(
                text = "Confirmation",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Are you sure you want to sign out?",
                textAlign = TextAlign.Center
            )
        },
        onConfirmButtonClick = onSignOutConfirmButtonClick,
        onDismissButtonClick = onSignOutDismissButtonClick
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    scrollBarBehavior: TopAppBarScrollBehavior,

    showBack: Boolean,
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit
) {
    CustomTopAppBar(
        title = "Settings",
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = onBackButtonOnTopNavBar) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
                }
            }

        }, scrollBarBehavior = scrollBarBehavior
    )
}


//@Preview(showBackground = true)
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen()
//}


/*@Preview(showBackground = true)
@Composable
fun ProviderAccountInfoDetailsPreview() {
    ProviderAccountInfoDetails(Modifier, {}, {})
}*/

/*@Preview(showBackground = true)
@Composable
fun ProviderAccountInfoPreview() {
    ProviderAccountInfo(Modifier, {}, viewModel())
}*/

//@Preview(showBackground = true)
//@Composable
//fun NewPasswordScreenPreview() {
//    NewPasswordScreen(Modifier, {})
//}

/*@Preview(showBackground = true)
@Composable
fun UserAccountInfoDetailsPreview() {
    UserAccountInfoDetails(Modifier, {}, {}, viewModel())
}*/

/*
@Preview(showBackground = true)
@Composable
fun UserAccountInfoPreview() {
    UserAccountInfo(Modifier, {}, viewModel())
}
*/

@Preview(showBackground = true)
@Composable
fun SecurityScreenPreview() {
    SecurityScreen(Modifier, {})
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountScreenPreview() {
    DeleteAccountScreen(Modifier, {})
}

@Preview(showBackground = true)
//@Composable
//fun DeleteAccountDialogPreview() {
//    DeleteAccountDialog(Modifier, {}, {})
//}

@Preview(showBackground = true)
@Composable
fun SignOutDialogPreview() {
    SignOutDialog(Modifier, {}, {})
}

//@Preview(showBackground = true)
//@Composable
//fun SettingsScreenContentPreview() {
//    SettingsScreenContent(Modifier, {}, {}, {}, {})
//}


@Preview(showBackground = true)
@Composable
fun SettingsTopBarPreview() {
    //  SettingsTopBar(modifier=Modifier,showBack=true){}
}