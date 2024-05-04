package com.example.graduationproject.presentation.accountinfo

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.presentation.common.signup.DisplayRequirements
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.LightBrown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserAccountInfoScreen(
    innerPadding :PaddingValues,
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    onBackButtonOnTopNavBar: () -> Unit,
    onBottomNavigationItemClick: (String) -> Unit,
    userAccountInfoViewModel: UserAccountInfoViewModel
) {
    Log.d("whoo", "UserAccountInfoDetails: ")

            UserAccountInfo(
                modifier.padding(top = innerPadding.calculateTopPadding() ),
                onAccountInfoDetailsClick = onAccountInfoDetailsClick,
                userAccountInfoViewModel = userAccountInfoViewModel
            )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserAccountInfoDetailsScreen(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit,
    onBottomNavigationItemClick: (String) -> Unit,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    userAccountInfoViewModel: UserAccountInfoViewModel
) {

            UserAccountInfoDetails(
                modifier.padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
                onSaveChangesClick = onSaveChangesClick,
                onPhotoChangeClick = onPhotoChangeClick,
                userAccountInfoViewModel = userAccountInfoViewModel
            )


}


@Composable
fun UserAccountInfo(
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    userAccountInfoViewModel: UserAccountInfoViewModel
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Account Info",
                Modifier
                    .fillMaxWidth(.9f)
                    .padding(horizontal = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = onAccountInfoDetailsClick) {
                Icon(imageVector = Icons.Filled.ManageAccounts, contentDescription = "Account Info")
            }
        }


        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.TopCenter) {
            SubcomposeAsyncImage(
                model = userAccountInfoViewModel.imageUri,
                clipToBounds = true,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(130.dp),
                loading = { CircularProgressIndicator(modifier.wrapContentSize()) },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_become),
                        contentDescription = ""
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "UserName",
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userAccountInfoViewModel.userName,
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 16.sp,
        )

//        Spacer(modifier = Modifier.height(32.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Phone Number",
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userAccountInfoViewModel.phone ,
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 16.sp,
        )

//        Spacer(modifier = Modifier.height(32.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Email",
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userAccountInfoViewModel.emailN ,
            Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 16.sp,
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

//        TextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//            ,
//            value = "Helli",
//            onValueChange = {},
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Black.copy(alpha = .2f)
//            )
//        )

    }


}

@Composable
fun UserAccountInfoDetails(
    modifier: Modifier = Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    userAccountInfoViewModel: UserAccountInfoViewModel
) {

    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()

    val usernameFocusRequester = remember { FocusRequester() }
    val cityFocusRequester = remember { FocusRequester() }
    val addressFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        userAccountInfoViewModel.imageUri = uri
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Box(modifier = Modifier.size(130.dp)) {
                Image(
                    painter = if (userAccountInfoViewModel.imageUri != null) rememberImagePainter(data = userAccountInfoViewModel.imageUri)!! else painterResource(
                        id = R.drawable.ic_default_account_pic                    ),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp),
                    alignment = Alignment.TopCenter,
                )
                IconButton(
                    onClick = { launcher.launch("image/*") },
                    Modifier
                        .padding(16.dp)
                        .border(
                            width = 4.dp,
                            shape = RoundedCornerShape(32.dp),
                            color = Color.Black
                        )
                        .align(Alignment.BottomEnd),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = LightBrown)
                ) {
                    Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = "Change Photo")
                }

            }

        }



        Text(
            text = "UserName",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        CustomTextField(
            modifier = Modifier
                .focusRequester(usernameFocusRequester)
                .onFocusChanged { focusState ->
                    userAccountInfoViewModel.isUsernameFocused.value = focusState.isFocused
                },
            fieldName = R.string.username,
            fieldValue = userAccountInfoViewModel.userName,
            onValueChange = { userAccountInfoViewModel.onUserNameChanged(it) },
            isError = userAccountInfoViewModel.userNameError,
        )
        DisplayRequirements(
            isFieldFocused = userAccountInfoViewModel.isUsernameFocused.value,
            requirements = userAccountInfoViewModel.usernameRequirements,
            fieldValue = userAccountInfoViewModel.userName
        )

/*        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "UserName") }
        )*/

        // TODO City DropDownMenu


        Text(
            text = "City",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    userAccountInfoViewModel.expanded = !userAccountInfoViewModel.expanded
                }
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
                    userAccountInfoViewModel.textfieldSize = coordinates.size.toSize()
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (userAccountInfoViewModel.selectedCityValue.isNotEmpty()) userAccountInfoViewModel.selectedCityValue else "City",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(7f)
            )

            Icon(
                imageVector = if (userAccountInfoViewModel.expanded) Icons.Filled.ArrowDropUp
                else Icons.Filled.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }


        DropdownMenu(
            expanded = userAccountInfoViewModel.expanded,
            onDismissRequest = { userAccountInfoViewModel.expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { userAccountInfoViewModel.textfieldSize.width.toDp() })

        ) {
            governorates.forEachIndexed { index, governorate ->
                DropdownMenuItem(
                    onClick = {
                        userAccountInfoViewModel.selectedCityIndex = index
                        userAccountInfoViewModel.selectedCityValue = governorate
                        userAccountInfoViewModel.expanded = false
                        userAccountInfoViewModel.cityError = false
                    },
                    text = {
                        Text(text = governorate)
                    }
                )
            }
        }

        Text(
            text = "Address",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        CustomTextField(
            modifier = Modifier.focusRequester(addressFocusRequester),

            fieldName = R.string.address,
            fieldValue = userAccountInfoViewModel.address,
            onValueChange = { userAccountInfoViewModel.onAddressChanged(it) },
            isError = userAccountInfoViewModel.addressError,

            )

        Text(
            text = "Email",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        CustomTextField(
            modifier = Modifier
                .focusRequester(emailFocusRequester)
                .onFocusChanged { focusState ->
                    userAccountInfoViewModel.isEmailNFocused.value = focusState.isFocused
                },

            fieldName = R.string.email,
            fieldValue = userAccountInfoViewModel.emailN,
            onValueChange = { userAccountInfoViewModel.onEmailChangedN(it) },
            isError = userAccountInfoViewModel.emailNError,

            )
        DisplayRequirements(
            isFieldFocused = userAccountInfoViewModel.isEmailNFocused.value,
            requirements = userAccountInfoViewModel.emailRequirements,
            fieldValue = userAccountInfoViewModel.emailN
        )

        Text(
            text = "Phone Number",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        CustomTextField(
            modifier = Modifier
                .focusRequester(phoneNumberFocusRequester)
                .onFocusChanged { focusState ->
                    userAccountInfoViewModel.isPhoneNumberFocused.value = focusState.isFocused
                },

            fieldName = R.string.phone,
            fieldValue = userAccountInfoViewModel.phone,
            onValueChange = { userAccountInfoViewModel.onPhoneChanged(it) },
            isError = userAccountInfoViewModel.phoneError,

            )

        DisplayRequirements(
            isFieldFocused = userAccountInfoViewModel.isPhoneNumberFocused.value,
            requirements = userAccountInfoViewModel.phoneNumberRequirements,
            fieldValue = userAccountInfoViewModel.phone
        )

        CustomButtonAndText(
            text = R.string.done,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            onClick = onSaveChangesClick
        )


    }

}

