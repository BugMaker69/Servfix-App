package com.example.graduationproject.presentation.accountinfo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.presentation.common.signup.DisplayRequirements
import com.example.graduationproject.presentation.common.signup.UserViewModel
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.LightBrown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoScreen(
    modifier: Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    userViewModel: UserViewModel

) {

    ProviderAccountInfo(
        modifier = modifier,
        onAccountInfoDetailsClick = onAccountInfoDetailsClick,
        userViewModel = userViewModel
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoDetailsScreen(
    modifier: Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    userViewModel: UserViewModel
) {

    ProviderAccountInfoDetails(
        modifier = modifier,
        onSaveChangesClick = onSaveChangesClick,
        onPhotoChangeClick = onPhotoChangeClick,
        userViewModel = userViewModel
    )

}


@Composable
fun ProviderAccountInfo(
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    userViewModel: UserViewModel
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Account Info",
                modifier = Modifier
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
            Image(
                painter = painterResource(id = R.drawable.servix_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp),
                alignment = Alignment.TopCenter,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "UserName",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userViewModel.returnedProviderData?.username ?: "",
            modifier = Modifier
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
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userViewModel.returnedProviderData?.phone ?: "",
            modifier = Modifier
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
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userViewModel.returnedProviderData?.email ?: "",
            modifier = Modifier
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


        Text(
            text = "Rate",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        //TODO RATE DESIGN
        Text(
            text = userViewModel.returnedProviderData?.ratings ?: "",
            modifier = Modifier
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


        Text(
            text = "Fixed Fees",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = userViewModel.returnedProviderData?.fixed_salary.toString() ?: "",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 16.sp,
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        /*        Spacer(modifier = Modifier.height(32.dp))


                Text(
                    text = "Number of transaction with customer",
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(horizontal = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "5",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .border(shape = RectangleShape, color = Color.Black, width = 2.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 16.sp,

                    )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))*/

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
fun ProviderAccountInfoDetails(
    modifier: Modifier = Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    userViewModel: UserViewModel

) {
    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()

    val usernameFocusRequester = remember { FocusRequester() }
    val cityFocusRequester = remember { FocusRequester() }
    val addressFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val services = context.resources.getStringArray(R.array.services).toList()

//    userViewModel.getDataFromServer()

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
                    painter = painterResource(id = R.drawable.servix_logo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp),
                    alignment = Alignment.TopCenter,
                )
                IconButton(
                    onClick = onPhotoChangeClick,
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
                text = if (userViewModel.returnedProviderData?.city?.isNotEmpty() == true) userViewModel.returnedProviderData!!.city else "City",
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


        /*        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "City") }
        )*/

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
            fieldValue = userViewModel.returnedProviderData?.address ?: "",
            onValueChange = { userViewModel.onAddressChanged(it) },
            isError = userViewModel.addressError,

            )

        /*        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Address") }*/


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


        /*        OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Email") }
                )*/

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
                    userViewModel.isPhoneNumberFocused.value = focusState.isFocused
                },

            fieldName = R.string.phone,
            fieldValue = userViewModel.returnedProviderData?.phone ?: "",
            onValueChange = { userViewModel.onPhoneChanged(it) },
            isError = userViewModel.phoneError,

            )

        DisplayRequirements(
            isFieldFocused = userViewModel.isPhoneNumberFocused.value,
            requirements = userViewModel.phoneNumberRequirements,
            fieldValue = userViewModel.phone
        )

        /*        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Phone") }*/
//        )
        // TODO Service DropDownMenu
        Text(
            text = "Service",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

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
                text = if (userViewModel.selectedServiceValue.isNotEmpty()) userViewModel.returnedProviderData!!.profession else "Your Service",
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
                    },
                    text = {
                        Text(text = service)
                    }
                )
            }
        }


        /*        OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Service") }
                )*/

        Text(
            text = "Fixed Fees",
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        CustomTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            fieldName = R.string.fixed_salary,
            fieldValue = userViewModel.returnedProviderData?.fixed_salary.toString(),
            onValueChange = { userViewModel.onFixedSalaryChanged(it) },
        )


        /*        OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Fixed Fees") }
                )*/

        /*        // TODO  My Work File Upload
                Text(
                    text = "My Work",
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )





                AddNeWorkToProfileItem()*/

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
