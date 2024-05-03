package com.example.graduationproject.presentation.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.presentation.common.RatingBar
import com.example.graduationproject.ui.theme.LightBlue

@Composable
fun FavoriteScreen(modifier: Modifier, favouriteViewModel: FavouriteViewModel,onProfileClicked: (Int) -> Unit
) {
    val lista by favouriteViewModel.providersFavList.collectAsState()

//    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        if (lista.value.isEmpty())
//            CircularProgressIndicator(color= Color.Black, modifier = modifier.padding(vertical = 10.dp))
//    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp)
    ) {


        items(lista) { state->
            FavoriteItem(state, onProfileClick = {id->
                                                 onProfileClicked(id)

            }, onCardClick = {
                id->
                favouriteViewModel.id=id
                favouriteViewModel.showDialog.value = true

            })

        }
    }
    if (favouriteViewModel.showDialog.value) {
FavouriteAlertDialog(onConfirmClick = {
favouriteViewModel.deleteFavourite(favouriteViewModel.id)
    favouriteViewModel.showDialog.value=false

}, onDismissClick = {
    favouriteViewModel.showDialog.value = false
})
    }
}
@Composable
fun FavouriteAlertDialog(onDismissClick:()->Unit,onConfirmClick:()->Unit){
    AlertDialog(
        text = { Text(text = stringResource(id = R.string.remove_favorite)) },
        onDismissRequest = {
            onDismissClick()

        },
        confirmButton = {
           Button(onClick = {onConfirmClick()}) {
               Text(text = stringResource(id = R.string.confirm))
           }
        },

        dismissButton = {
Button(onClick = {onDismissClick()}) {
    Text(text = stringResource(id = R.string.cancel))

}

        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteItem(state:ReturnedProviderData, onCardClick: (Int) -> Unit, onProfileClick:(Int)->Unit) {
//    val shape = object : Shape {
//        override fun createOutline(
//            size: Size,
//            layoutDirection: LayoutDirection,
//            density: Density
//        ): Outline {
//            return Outline.Generic(Path().apply {
//                // Start point
//                moveTo(0f, size.height)
//                // Line to bottom right
//                lineTo(size.width, size.height)
//                // Line to top right
//                lineTo(size.width, size.height / 2)
//                // Create arc for cut out
//                arcTo(
//                    Rect(
//                        left = size.width / 2 - size.height / 4,
//                        top = size.height / 2 - size.height / 4,
//                        right = size.width / 2 + size.height / 4,
//                        bottom = size.height / 2 + size.height / 4
//                    ), startAngleDegrees = 0f, sweepAngleDegrees = 180f, forceMoveTo = false
//                )
//                // Line to top left
//                lineTo(0f, size.height / 2)
//                // Close path
//                close()
//            })
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 45.dp, horizontal = 14.dp)
    ) {
        Card(
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(80.dp),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .combinedClickable(onClick = {
                    onProfileClick(state.id)

                },onLongClick = {
                    onCardClick(state.id)
                }
                )

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                RatingBar(rating = 3.7, modifier = Modifier.padding(10.dp))
                Text(
                    text = state.profession,
                    Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .background(LightBlue)
                )

            }
            Text(
                text = state.username, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold
            )
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = state.phone, Modifier.padding(10.dp))



                Text(text = state.city, Modifier.padding(10.dp))

            }


        }
        SubcomposeAsyncImage(
            model = state.image,
            clipToBounds = true,
            contentDescription = "",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-50).dp)
                .offset(x = (15).dp)

            ,
            loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
           error = {
           Image(
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = ""
                )
         }
        )
//        Image(
//            modifier = Modifier
//                .size(100.dp)
//                .align(Alignment.TopCenter)
//                .offset(y = (-50).dp) // Adjust this value as needed
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop,
//            alignment = Alignment.Center,
//            painter = painterResource(id = R.drawable.person),
//            contentDescription = ""
//        )
    }
}





@Preview
@Composable
fun FavoriteItemPrev() {
    //   FavoriteItem()
}