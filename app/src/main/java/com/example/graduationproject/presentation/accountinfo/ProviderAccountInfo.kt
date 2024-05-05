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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Cancel
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
import com.example.graduationproject.R
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.presentation.common.signup.DisplayRequirements
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.LightBrown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoScreen(
    modifier: Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    onAddWorkToProfileItemOpenPhoto: (Int,String) -> Unit,
    onAddWorkToProfile: () -> Unit,
//    onDeleteIconClick: (Int) -> Unit,
    getWorks: List<GetWorksItem>,
    providerAccountInfoViewModel: ProviderAccountInfoViewModel

) {

    ProviderAccountInfo(
        modifier = modifier,
        onAccountInfoDetailsClick = onAccountInfoDetailsClick,
        onAddWorkToProfileItemOpenPhoto = {id,name-> onAddWorkToProfileItemOpenPhoto(id,name) },
        onAddWorkToProfile = onAddWorkToProfile,
//        onDeleteIconClick = onDeleteIconClick,
        getWorks = getWorks,
        providerAccountInfoViewModel = providerAccountInfoViewModel
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoDetailsScreen(
    modifier: Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    providerAccountInfoViewModel: ProviderAccountInfoViewModel
) {

    ProviderAccountInfoDetails(
        modifier = modifier,
        onSaveChangesClick = onSaveChangesClick,
        onPhotoChangeClick = onPhotoChangeClick,
        providerAccountInfoViewModel = providerAccountInfoViewModel
    )

}


@Composable
fun ProviderAccountInfo(
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    onAddWorkToProfileItemOpenPhoto: (Int,String) -> Unit,
    onAddWorkToProfile: () -> Unit,
//    onDeleteIconClick: (Int) -> Unit,
    getWorks: List<GetWorksItem>,
    providerAccountInfoViewModel: ProviderAccountInfoViewModel
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
            SubcomposeAsyncImage(
                model = providerAccountInfoViewModel.imageUri,
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
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = providerAccountInfoViewModel.userName,
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
            text = providerAccountInfoViewModel.phone,
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
            text = providerAccountInfoViewModel.emailN,
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
            text = providerAccountInfoViewModel.rating,
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
            text = providerAccountInfoViewModel.fixedSalary,
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Work",
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(horizontal = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = onAddWorkToProfile) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD Work")
            }
        }

        LazyVerticalGrid(
            GridCells.Fixed(3), modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp)
        ) {
            items(getWorks) { work ->
                Column {


                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(4.dp)
                            .clickable {
                                Log.d("ProviderAccountInfo", "ProviderAccountInfo: ${work.id}")
                                Log.d("ProviderAccountInfo", "ProviderAccountInfo: ${work.image}")
                                onAddWorkToProfileItemOpenPhoto(work.id, work.image)
                            }
//            .clip(RoundedCornerShape(16.dp))
                    ) {
/*                        Icon(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .clickable { onDeleteIconClick },
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = ""
                        )*/

                        SubcomposeAsyncImage(
                            model = Constant.BASE_URL + work.image,
                            clipToBounds = true,
                            contentDescription = "",
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .aspectRatio(1f),
                            contentScale = ContentScale.Inside,
                            loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
                            error = {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_become),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(200.dp)
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Inside
                                )
                            }
                        )
                    }
                }
            }
        }


        /*
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center
                ) {

                    items(5) {
                        Image(painter = painterResource(id = R.drawable.ic_become), contentDescription = "")
                    }

        */
        /*            items(getWorks) { item ->
                        AddWorkToProfileItem(
                            onAddWorkToProfileItemOpenPhoto = onAddWorkToProfileItemOpenPhoto,
                            image = item.image
                        )
                    }*//*

        }
*/


        /*
                LazyRow(

                ) {

                    items(5) {
                        Image(painter = painterResource(id = R.drawable.ic_become), contentDescription = "")
                    }

                    items(getWorks) { item ->
                        AddWorkToProfileItem(
                            onAddWorkToProfileItemOpenPhoto = onAddWorkToProfileItemOpenPhoto,
                            image = item.image
                        )
                    }

                }
        */


        /*        Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )*/

    }


}

@Composable
fun ProviderAccountInfoDetails(
    modifier: Modifier = Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
    providerAccountInfoViewModel: ProviderAccountInfoViewModel

) {
    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()

    val usernameFocusRequester = remember { FocusRequester() }
    val cityFocusRequester = remember { FocusRequester() }
    val addressFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val services = context.resources.getStringArray(R.array.services).toList()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            providerAccountInfoViewModel.imageUri = uri
        }
    val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

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
                ///edit this
                SubcomposeAsyncImage(
                    model = providerAccountInfoViewModel.imageUri,
                    clipToBounds = true,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(200.dp),
                    loading = { CircularProgressIndicator(modifier.wrapContentSize()) },
                    error = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_become),
                            contentDescription = ""
                        )
                    }
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
                    providerAccountInfoViewModel.isUsernameFocused.value = focusState.isFocused
                },
            fieldName = R.string.username,
            fieldValue = providerAccountInfoViewModel.userName,
            onValueChange = { providerAccountInfoViewModel.onUserNameChanged(it) },
            isError = providerAccountInfoViewModel.userNameError,
        )
        DisplayRequirements(
            isFieldFocused = providerAccountInfoViewModel.isUsernameFocused.value,
            requirements = providerAccountInfoViewModel.usernameRequirements,
            fieldValue = providerAccountInfoViewModel.userName
        )


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
                    providerAccountInfoViewModel.expanded = !providerAccountInfoViewModel.expanded
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
                    providerAccountInfoViewModel.textfieldSize = coordinates.size.toSize()
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (providerAccountInfoViewModel.selectedCityValue.isNotEmpty()) providerAccountInfoViewModel.selectedCityValue else "City",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(7f)
            )

            Icon(
                imageVector = if (providerAccountInfoViewModel.expanded) Icons.Filled.ArrowDropUp
                else Icons.Filled.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }


        DropdownMenu(
            expanded = providerAccountInfoViewModel.expanded,
            onDismissRequest = { providerAccountInfoViewModel.expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { providerAccountInfoViewModel.textfieldSize.width.toDp() })

        ) {
            governorates.forEachIndexed { index, governorate ->
                DropdownMenuItem(
                    onClick = {
                        providerAccountInfoViewModel.selectedCityIndex = index
                        providerAccountInfoViewModel.selectedCityValue = governorate
                        providerAccountInfoViewModel.expanded = false
                        providerAccountInfoViewModel.cityError = false
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
            fieldValue = providerAccountInfoViewModel.address,
            onValueChange = { providerAccountInfoViewModel.onAddressChanged(it) },
            isError = providerAccountInfoViewModel.addressError,

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
                    providerAccountInfoViewModel.isEmailNFocused.value = focusState.isFocused
                },

            fieldName = R.string.email,
            fieldValue = providerAccountInfoViewModel.emailN,
            onValueChange = { providerAccountInfoViewModel.onEmailChangedN(it) },
            isError = providerAccountInfoViewModel.emailNError,

            )
        DisplayRequirements(
            isFieldFocused = providerAccountInfoViewModel.isEmailNFocused.value,
            requirements = providerAccountInfoViewModel.emailRequirements,
            fieldValue = providerAccountInfoViewModel.emailN
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
                    providerAccountInfoViewModel.isPhoneNumberFocused.value = focusState.isFocused
                },

            fieldName = R.string.phone,
            fieldValue = providerAccountInfoViewModel.phone,
            onValueChange = { providerAccountInfoViewModel.onPhoneChanged(it) },
            isError = providerAccountInfoViewModel.phoneError,

            )

        DisplayRequirements(
            isFieldFocused = providerAccountInfoViewModel.isPhoneNumberFocused.value,
            requirements = providerAccountInfoViewModel.phoneNumberRequirements,
            fieldValue = providerAccountInfoViewModel.phone
        )

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
                .clickable {
                    providerAccountInfoViewModel.expandedService =
                        !providerAccountInfoViewModel.expandedService
                }
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
                    providerAccountInfoViewModel.textfieldServiceSize = coordinates.size.toSize()
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (providerAccountInfoViewModel.selectedServiceValue.isNotEmpty()) providerAccountInfoViewModel.selectedServiceValue else "Your Service",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(7f)
            )

            Icon(
                imageVector = if (providerAccountInfoViewModel.expandedService) Icons.Filled.ArrowDropUp
                else Icons.Filled.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        DropdownMenu(
            expanded = providerAccountInfoViewModel.expandedService,
            onDismissRequest = { providerAccountInfoViewModel.expandedService = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { providerAccountInfoViewModel.textfieldServiceSize.width.toDp() })

        ) {
            services.forEachIndexed { index, service ->
                DropdownMenuItem(
                    onClick = {
                        providerAccountInfoViewModel.selectedServiceIndex = index
                        providerAccountInfoViewModel.selectedServiceValue = service
                        providerAccountInfoViewModel.expandedService = false
                    },
                    text = {
                        Text(text = service)
                    }
                )
            }
        }


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
            fieldValue = providerAccountInfoViewModel.fixedSalary,
            onValueChange = { providerAccountInfoViewModel.onFixedSalaryChanged(it) },
        )


        CustomButtonAndText(
            text = R.string.done,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            onClick = {
                Log.d(
                    "TAG",
                    "ProviderAccountInfoDetails: $providerAccountInfoViewModel || ${providerAccountInfoViewModel.userName} || ${providerAccountInfoViewModel.phone} || ${providerAccountInfoViewModel.emailN} ||"
                )
                Log.d("WHY", "IMageURI ${providerAccountInfoViewModel.imageUri}")
                onSaveChangesClick()
            }
        )

    }
}
