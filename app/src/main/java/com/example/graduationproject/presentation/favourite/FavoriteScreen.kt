package com.example.graduationproject.presentation.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.RatingBar
import com.example.graduationproject.ui.theme.LightBlue
import com.example.graduationproject.ui.theme.OrangeRate
import kotlin.math.roundToInt

@Composable
fun FavoriteScreen(modifier: Modifier) {
    val favouriteViewModer: FavouriteViewModel = viewModel()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        items(10) {
            FavoriteItem {
                favouriteViewModer.showDialog.value = true

            }
        }
    }
    if (favouriteViewModer.showDialog.value) {
FavouriteAlertDialog(onConfirmClick = {}, onDismissClick = {
    favouriteViewModer.showDialog.value = false
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
fun FavoriteItem(onCardClick: () -> Unit) {
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
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .combinedClickable(onLongClick = {
                    onCardClick()
                }) {

                }

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                RatingBar(rating = 3.7, modifier = Modifier.padding(10.dp))
                Text(
                    text = "Electricity",
                    Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .background(LightBlue)
                )

            }
            Text(
                text = "Ahmed Ramadan", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
            )
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "01025659292", Modifier.padding(10.dp))



                Text(text = "Alexandria", Modifier.padding(10.dp))

            }


        }

        Image(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-50).dp) // Adjust this value as needed
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.person),
            contentDescription = ""
        )
    }
}





@Preview
@Composable
fun FavoriteItemPrev() {
    //   FavoriteItem()
}