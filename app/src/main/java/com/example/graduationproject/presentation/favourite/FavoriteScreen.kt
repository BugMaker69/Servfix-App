package com.example.graduationproject.presentation.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.OrangeRate
import kotlin.math.roundToInt

@Composable
fun FavoriteScreen(modifier: Modifier) {

    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(10) {
            FavoriteItem()
        }
    }

}

@Composable
fun FavoriteItem() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)) {
            Image(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_hire),
                contentDescription = ""
            )
        }

        Text(text = "Ahmed Ramadan", modifier = Modifier.align(Alignment.CenterHorizontally))
        RatingBar(rating = 3.7)
        Text(text = "01025659292")
        Text(text = "electricity")

    }
}

@Composable
fun RatingBar(rating: Double) {
    val roundedRating = (rating * 2).roundToInt() / 2.0
    val fullStars = roundedRating.toInt()
    val halfStar = if (roundedRating - fullStars >= 0.5) 1 else 0

    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = OrangeRate
            )
        }

        if (halfStar == 1) {
            Icon(
                imageVector = Icons.Filled.StarHalf,
                contentDescription = null,
                tint = OrangeRate
            )
        }

        repeat(5 - fullStars - halfStar) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.LightGray
            )
        }

        // Add space between stars
        if (fullStars + halfStar != 5) {
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}


@Preview
@Composable
fun FavoriteItemPrev() {
    FavoriteItem()
}