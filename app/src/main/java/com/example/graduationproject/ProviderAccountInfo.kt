package com.example.graduationproject

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.LightBrown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoScreen(
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit,
    onBackButtonOnTopNavBar: () -> Unit,
    onBottomNavigationItemClick: (String) -> Unit,
) {
    ScreensTemplate(
        topBar = { SettingsTopBar(onBackButtonOnTopNavBar = onBackButtonOnTopNavBar) },
        bottomBar = { BottomAppBar(onBottomNavigationItemClick = onBottomNavigationItemClick) },
        content = {
            ProviderAccountInfo(
                modifier.padding(top = it.calculateTopPadding() - 10.dp),
                onAccountInfoDetailsClick = onAccountInfoDetailsClick
            )
        }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProviderAccountInfoDetailsScreen(
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit,
    onBottomNavigationItemClick: (String) -> Unit,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit,
) {
    ScreensTemplate(
        topBar = { SettingsTopBar(onBackButtonOnTopNavBar = onBackButtonOnTopNavBar) },
        bottomBar = { BottomAppBar(onBottomNavigationItemClick = onBottomNavigationItemClick) },
        content = {
            ProviderAccountInfoDetails(
                modifier.padding(top = it.calculateTopPadding() - 10.dp),
                onSaveChangesClick = onSaveChangesClick,
                onPhotoChangeClick = onPhotoChangeClick
            )
        }
    )
}


@Composable
fun ProviderAccountInfo(
    modifier: Modifier = Modifier,
    onAccountInfoDetailsClick: () -> Unit
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
            text = "Actual Name",
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
            text = "Actual Number",
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
            text = "Actual Email",
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

        Text(
            text = "Rating Stars",
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
            text = "50",
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
fun ProviderAccountInfoDetails(
    modifier: Modifier = Modifier,
    onSaveChangesClick: () -> Unit,
    onPhotoChangeClick: () -> Unit
) {


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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "UserName") }
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "City") }
        )

        Text(
            text = "Address",
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
            placeholder = { Text(text = "Address") }
        )

        Text(
            text = "Email",
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
            placeholder = { Text(text = "Email") }
        )

        Text(
            text = "Phone Number",
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
            placeholder = { Text(text = "Phone") }
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Service") }
        )

        Text(
            text = "Fixed Fees",
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
            placeholder = { Text(text = "Fixed Fees") }
        )

        // TODO  My Work File Upload
        Text(
            text = "Fixed Fees",
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
            placeholder = { Text(text = "Fixed Fees") }
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
