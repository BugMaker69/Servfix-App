package com.example.graduationproject.presentation.search_for_provider

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.ui.theme.OrangeRate



@Composable
fun FindProvider(
    modifier: Modifier,
    viewProfileClick: (Int) -> Unit,
     findProviderViewModel:FindProviderViewModel

    ) {

    val searchText by findProviderViewModel.searchText.collectAsState()
    val lista= findProviderViewModel.lista.collectAsLazyPagingItems()
    if (findProviderViewModel.showDialog.value) {
        CustomDialog(modifier = modifier
            .size(height = 350.dp, width = 400.dp), onConfirmButtonClick = {
            findProviderViewModel.showRemoveIcon()
            findProviderViewModel.categoryFilteration()
           findProviderViewModel.dismissDialog()
        }, onDismissButtonClick = {
            findProviderViewModel.dismissDialog()
        }, confirmButtonText = "confirm", dismissButtonText = "dismiss"
        ) {

            FilterScreen(
                findProviderViewModel.rating,
                expandedValue = findProviderViewModel.expandend.value,
                onExpandedChange = {
                    findProviderViewModel.expandend.value = !findProviderViewModel.expandend.value
                },
                selectedCity = findProviderViewModel.selectedCity.value,
                onExpandedDismiss = { findProviderViewModel.changeExpandedValue() },
                onDropMenuClick = { selectionOption ->
                    findProviderViewModel.selectedCity.value = selectionOption
                    findProviderViewModel.changeExpandedValue()
                },
                onRateClick = { starRating ->
                    findProviderViewModel.rating = starRating
                }
            )
        }
    }


    LazyColumn(
        modifier = modifier
            .fillMaxSize(), contentPadding = PaddingValues(2.dp)
    ) {
        item {
            TopFavouriteItem(
                findProviderViewModel.serviceName,
                findProviderViewModel.selectedCity.value,
                searchText,
                onSearchTextChanged = { it ->
                    findProviderViewModel.onSearchTextChange(it)
                },
                onShowDialog = {
                    findProviderViewModel.showDialog.value = true
                },
                onRemoveFilterClick = {
                    findProviderViewModel.removeFilter()
                    findProviderViewModel.showRemoveIcon.value = false
                }, showRemoveIcon = findProviderViewModel.showRemoveIcon.value

            )
        }
items(lista.itemCount){index->
    val item=lista[index]
    item?.let{provider->
                ProviderItem(Modifier, provider) {id->
                    viewProfileClick (id)}
            }
}
    }

}

@Composable
fun TopFavouriteItem(
    serviceName: String,
    selectedCity: String,
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onShowDialog: () -> Unit,
    showRemoveIcon:Boolean,
    onRemoveFilterClick:()->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "location")

        Text(text = serviceName)
        Divider(
            color = Gray, modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .width(1.dp)
                .height(15.dp)
        )
        Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "location")
        Text(text = selectedCity)
        Spacer(modifier = Modifier.weight(1f))
if(showRemoveIcon){
    Icon(imageVector = Icons.Filled.Cancel, contentDescription ="Cancel",
        Modifier
            .padding(horizontal = 10.dp)
            .clickable {
                onRemoveFilterClick()
            })
}



    }
    TextField(
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "") },
        value = searchText,
        onValueChange =   { newText -> onSearchTextChanged(newText) },
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
            onShowDialog()
        }
    ) {
        Text(text = stringResource(id = R.string.filters))
        Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "location")


    }
}

@Composable
fun ProviderItem(modifier: Modifier, state: ReturnedProviderData,viewProfileClick:(Int)->Unit) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .clickable {
                viewProfileClick(state.id)
            },
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Row(modifier = modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(5.dp)
                    .clip(CircleShape)
            ) {
                SubcomposeAsyncImage(
                    model = state.image,
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
                        state.ratings, modifier = Modifier.weight(0.3f)
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
fun FilterScreen(
    rating: Int,
    expandedValue: Boolean,
    onExpandedChange: () -> Unit,
    selectedCity: String,
    onExpandedDismiss: () -> Unit,
    onDropMenuClick: (String) -> Unit,
    onRateClick: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier
            .fillMaxSize()
            .let { if (isLandscape) it.verticalScroll(rememberScrollState()) else it },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Filters", modifier = Modifier.padding(16.dp))
        LocationsSection(
            expandedValue = expandedValue,
            onExpandedChange = onExpandedChange,
            selectedCity = selectedCity, onExpandedDismiss = onExpandedDismiss,
            onDropMenuClick = onDropMenuClick
        )
        RateSection(rating = rating, onRateClick = onRateClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsSection(
    expandedValue: Boolean,
    onExpandedChange: () -> Unit,
    selectedCity: String,
    onExpandedDismiss: () -> Unit,
    onDropMenuClick: (String) -> Unit
) {
    val context = LocalContext.current
    val governorates = context.resources.getStringArray(R.array.egypt_governorates).toList()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(text = "Locations")
        Divider(thickness = 1.dp, modifier = Modifier.padding(3.dp))
        Spacer(modifier = Modifier.padding(5.dp))

        ExposedDropdownMenuBox(modifier = Modifier.padding(4.dp),

            expanded = expandedValue,
            onExpandedChange = {
                onExpandedChange()
            }) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedCity,
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "location icon"
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedValue) },

                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                )


            )
            ExposedDropdownMenu(
                modifier = Modifier.requiredSizeIn(maxHeight = 200.dp), // Set your desired maxHeight
                expanded = expandedValue,
                onDismissRequest = onExpandedDismiss
            ) {
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
                        onClick = { onDropMenuClick(selectionOption) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                    )
                }
            }
        }
    }
}

@Composable
fun RateSection(rating: Int, onRateClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(text = "Rate")
        Divider(thickness = 1.dp, modifier = Modifier.padding(4.dp))
        RatingBar(rating = rating, onRateClick = onRateClick)


    }
}

@Composable
fun RatingBar(rating: Int, modifier: Modifier = Modifier, onRateClick: (Int) -> Unit) {


    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        repeat(5) { index ->
            val starRating = index + 1

            Icon(
                imageVector = when {
                    starRating <= rating -> Icons.Filled.Star
                    else -> Icons.Filled.StarOutline
                },
                contentDescription = null,
                tint = if (starRating <= rating) OrangeRate else Color.LightGray,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onRateClick(starRating)
                    }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProviderItemPrev() {
//    GraduationProjectTheme {
//        FindProvider(Modifier)
//
//    }
//}