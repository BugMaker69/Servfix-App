package com.example.graduationproject.presentation.userservice

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun ShareProblemScreen(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onShareClick: () -> Unit,
    serviceViewModel: ServiceViewModel
) {


    val context = LocalContext.current

    val services = context.resources.getStringArray(R.array.services).toList()
    val locations = context.resources.getStringArray(R.array.egypt_governorates).toList()


    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri:Uri? ->
            serviceViewModel.imageUri = uri
        }

    /*    val cameraLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { uri ->
                serviceViewModel.imageUri = uri
            }*/
    /*
        val cameraLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { uri ->
                serviceViewModel.handleCameraResult(uri)
            }
    */


    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            serviceViewModel.handlePermissionResult(isGranted)
        }


    /*    if (serviceViewModel.launchCamera.value) {
            cameraLauncher.launch(null)
            serviceViewModel.launchCamera.value = false
        }*/

    if (serviceViewModel.permissionDenied.value) {
        Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        serviceViewModel.permissionDenied.value = false
    }

    LaunchedEffect(serviceViewModel.permissionDenied.value) {
        if (serviceViewModel.permissionDenied.value) {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            serviceViewModel.permissionDenied.value = false
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            IconButton(onClick = onCancelClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }

            CustomButtonAndText(
                text = R.string.share_problem,
                alignment = Alignment.Center,
                contentColor = Color.Black,
                fontSize = 32,
            )
        }

        Divider(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .background(Color.LightGray)
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")

                    Column(
                        Modifier
                            .fillMaxWidth(.6f)
                            .padding(horizontal = 4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "User")

                        Row(
                            modifier = Modifier
                                .clickable {
                                    serviceViewModel.expandDropDownMenu =
                                        !serviceViewModel.expandDropDownMenu
                                }
                                .padding(start = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (serviceViewModel.selectedSentToServiceValue != "") serviceViewModel.selectedSentToServiceValue else "Send To")
                            if (serviceViewModel.expandDropDownMenu) {
                                Icon(Icons.Default.ExpandLess, contentDescription = "Arrow Icon")
                            } else {
                                Icon(Icons.Default.ExpandMore, contentDescription = "Arrow Icon")
                            }
                        }

                        DropdownMenu(
                            expanded = serviceViewModel.expandDropDownMenu,
                            onDismissRequest = { serviceViewModel.expandDropDownMenu = false },
                        ) {
                            services.forEachIndexed { index, service ->
                                DropdownMenuItem(
                                    text = { Text(text = service) },
                                    onClick = {
                                        serviceViewModel.selectedSentToServiceIndex = index
                                        serviceViewModel.selectedSentToServiceValue = service
                                        serviceViewModel.expandDropDownMenu = false
                                    }
                                )
                            }
                        }

                    }

                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                    Column(
                        Modifier
//                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    serviceViewModel.expandDropDownMenuLocation =
                                        !serviceViewModel.expandDropDownMenuLocation
                                }
                                .padding(start = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (serviceViewModel.selectedSentToLocationValue != "") serviceViewModel.selectedSentToLocationValue else "Location?")
                            /*
                        if (serviceViewModel.expandDropDownMenu) {
                            Icon(Icons.Default.ExpandLess, contentDescription = "Arrow Icon")
                        } else {
                            Icon(Icons.Default.ExpandMore, contentDescription = "Arrow Icon")
                        }
*/
                        }

                        DropdownMenu(
                            expanded = serviceViewModel.expandDropDownMenuLocation,
                            onDismissRequest = { serviceViewModel.expandDropDownMenuLocation = false }
                            ,
                        ) {
                            locations.forEachIndexed { index, location ->
                                DropdownMenuItem(
                                    text = { Text(text = location) },
                                    onClick = {
                                        serviceViewModel.selectedSentToLocationIndex = index
                                        serviceViewModel.selectedSentToLocationValue = location
                                        serviceViewModel.expandDropDownMenuLocation = false
                                    }
                                )
                            }
                        }
//                    Text(text = "Location")
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.85f)

                ) {
                    Column {

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp),
                            value = serviceViewModel.postText,
                            onValueChange = {
                                serviceViewModel.onPostTextChange(it)
                            },
                            maxLines = 15,
                            colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = "Limit ${serviceViewModel.postText.length}/${serviceViewModel.maxPostTextLength}",
                            textAlign = TextAlign.End
                        )

                    }

                }



                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(width = 1.dp, color = Color.Gray),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Add to your post",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .weight(7f)
                    )
                    IconButton(
                        onClick = {
//                            if (serviceViewModel.imageMap.size != serviceViewModel.maxImages) {
                            serviceViewModel.startLoading()
                            galleryLauncher.launch("image/*")
//                            }
                        },
                        enabled = !serviceViewModel.isLoading.value,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .weight(2f)
                    ) {
                        if (serviceViewModel.isLoading.value) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                        } else {
                            Icon(imageVector = Icons.Default.Image, contentDescription = "")
                        }
                    }
                    IconButton(
                        onClick = {
//                            if (serviceViewModel.imageMap.size != serviceViewModel.maxImages)
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        },
                        enabled = !serviceViewModel.isLoading.value,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .weight(2f)
                    ) {
                        if (serviceViewModel.isLoading.value) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                        } else {
                            Icon(imageVector = Icons.Default.AddAPhoto, contentDescription = "")
                        }
                    }
                }

//                LazyRow(
//                    modifier = Modifier.padding(top = 16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
                if (serviceViewModel.imageUri!=null) {
                    Box {
                        Image(
                            painter = rememberImagePainter(data = Uri.parse(serviceViewModel.imageUri.toString()))!!,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        IconButton(
                            onClick = { serviceViewModel.removeImage() },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Remove image")
                        }
                    }
                }

//                }

                /*                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    textAlign = TextAlign.End,
                                    text = if (serviceViewModel.imageMap.size == serviceViewModel.maxImages) {
                                        "Sorry But You Reached Max Images Allowed You Need To remove First"
                                    } else {
                                        "Total Number Of Photos: ${serviceViewModel.imageMap.size}/ ${serviceViewModel.maxImages}"
                                    },
                                    color = if (serviceViewModel.imageMap.size == serviceViewModel.maxImages) Color.Green else Color.Black
                                )*/
            }
        }



        CustomButtonAndText(
            text = R.string.share,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            backgroundColor = DarkBlue,
            contentColor = Color.White,
            onClick = {
                serviceViewModel.shareCreatePost()
            }
        )

    }

}


@Preview(showBackground = true)
@Composable
fun ShareProblemPreview() {
    ShareProblemScreen(Modifier, {}, {}, viewModel())
}