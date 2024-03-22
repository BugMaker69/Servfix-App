package com.example.graduationproject.presentation.search_for_provider

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.R
import com.example.graduationproject.data.ServiceProviderCard
import com.example.graduationproject.data.ServiceProviderSearch
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.presentation.common.HomeTopBar
import com.example.graduationproject.ui.theme.GraduationProjectTheme
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindProvider(
    modifier: Modifier,
    onNotificationClick: () -> Unit,
    onMessageClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val findProviderViewModel: FindProviderViewModel = viewModel()

    val serviceProviders by findProviderViewModel._serviceProviders.collectAsState()

    val searchText by findProviderViewModel.searchText.collectAsState()
    if (findProviderViewModel.showDialog.value) {
        CustomDialog(onConfirmButtonClick = {
            findProviderViewModel.dismissDialog()
        }, onDismissButtonClick = {
            Log.d("Done", "FindProvider: ")
            findProviderViewModel.dismissDialog()
        }, confirmButtonText = "confirm", dismissButtonText = "dismiss") {
            FilterScreen(findProviderViewModel)
        }
    }


        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                , contentPadding = PaddingValues(2.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "location")

                    Text(text = "category")
                    Divider(
                        color = Gray, modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .width(1.dp)
                            .height(15.dp)
                    )
                    Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "location")
                    Text(text = findProviderViewModel.selectedCity.value)


                }
                TextField(
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "") },
                    value = searchText,
                    onValueChange = findProviderViewModel::onSearchTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), colors = TextFieldDefaults.colors(
                        focusedContainerColor = White,
                        unfocusedContainerColor = White,
                    ),
                    placeholder = {
                        Text(
                            text = "search"
                        )
                    }
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable {
                        findProviderViewModel.showDialog.value = true
                    }
                ) {
                    Text(text = stringResource(id = R.string.filters))
                    Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "location")


                }
            }
            items(serviceProviders){
                ProviderItem(Modifier, it)
            }
//            items(serviceProviders) {
//
//            }

        }
//            LazyVerticalGrid(columns = GridCells.Fixed(2) ){
//        items (20){
//        ProviderItem(modifier,findProviderViewModel.testState.get(0))
//       }
        //}

    }


@Composable
fun ProviderItem(modifier: Modifier, state: ServiceProviderSearch) {
    Card(
        colors = CardDefaults.cardColors(containerColor = White),
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Row(modifier = modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp),
                )
            }



            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                ) {
                    Text(text = state.username, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "location",
                        tint = Gray,
                        modifier = Modifier.weight(0.2f)
                    )
                    Text(state.city, color = Gray, modifier = Modifier.weight(1f))
                    Divider(
                        color = Gray, modifier = modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .width(1.dp)
                            .height(15.dp)
                    )

                    Icon(
                        modifier = Modifier.weight(0.2f),
                        imageVector = Icons.Filled.Star,
                        contentDescription = "rate",
                        tint = Yellow,

                        )
                    Text(
                        state.ratings.toString(), modifier = Modifier.weight(0.3f)
                    )
                    Divider(
                        color = Gray, modifier = modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .width(1.dp)
                            .height(15.dp)

                    )
                    Icon(imageVector = Icons.Filled.Handshake, contentDescription = "")
                    Text(
                        text = "100 ",

                        modifier = modifier.weight(0.7f)
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = state.fixed_salary.toString() + " " + stringResource(id = R.string.Egyptian_Currency),
                        fontWeight = FontWeight.Bold
                    )



                }


            }
        }

    }
}

@Composable
fun FilterScreen(findProviderViewModel: FindProviderViewModel) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Filters", modifier = Modifier.padding(16.dp))

        LocationsSection(findProviderViewModel)
        RateSection()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsSection(findProviderViewModel: FindProviderViewModel) {
    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Locations")

        ExposedDropdownMenuBox(

            expanded = findProviderViewModel.expandend.value,
            onExpandedChange = {
                findProviderViewModel.expandend.value = !findProviderViewModel.expandend.value
            }) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = findProviderViewModel.selectedCity.value,
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "location icon"
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = findProviderViewModel.expandend.value) },

                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                )


            )
            ExposedDropdownMenu(
                modifier = Modifier.requiredSizeIn(maxHeight = 200.dp), // Set your desired maxHeight
                expanded = findProviderViewModel.expandend.value,
                onDismissRequest = { findProviderViewModel.expandend.value = false }) {
                governorates.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier.wrapContentSize(),
                        text = {
                            Text(
                                selectionOption,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        onClick = {
                            findProviderViewModel.selectedCity.value = selectionOption
                            findProviderViewModel.expandend.value = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                    )
                }
            }
        }
    }
}

@Composable
fun RateSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Rate")
        // TODO: Implement the rating stars and checkboxes
    }
}

@Preview(showBackground = true)
@Composable
fun ProviderItemPrev() {
    //   ProviderItem(modifier = Modifier,SeriveProviderCard())
    GraduationProjectTheme {
        FindProvider(Modifier, {}, onBackClick = {}, onMessageClick = {})

    }
}