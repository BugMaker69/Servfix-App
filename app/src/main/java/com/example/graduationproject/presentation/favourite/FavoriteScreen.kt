package com.example.graduationproject.presentation.favourite


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.graduationproject.presentation.LoadingScreen
import com.example.graduationproject.presentation.common.RatingBar
import com.example.graduationproject.ui.theme.LightBlue
import kotlinx.coroutines.delay

@Composable
fun FavoriteScreen(
    modifier: Modifier, favouriteViewModel: FavouriteViewModel, onProfileClicked: (Int) -> Unit
) {
    val lista by favouriteViewModel.providersFavList.collectAsState()
    if(favouriteViewModel.loading){
        LoadingScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        items(lista) { state ->
            SwipeToDeleteContainer(
                item = state,
                onDelete = {it->
                    favouriteViewModel.deleteFavourite(it.id)
                }
            ) { item ->
                FavoriteItem(item, onProfileClick = { id ->
                    onProfileClicked(id)

                }, onCardClick = { id ->
                    favouriteViewModel.id = id

                })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteItem(
    state: ReturnedProviderData,
    onCardClick: (Int) -> Unit,
    onProfileClick: (Int) -> Unit
) {
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

                }, onLongClick = {
                    onCardClick(state.id)
                }
                )

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                RatingBar(rating = state.ratings.toDouble(), modifier = Modifier.padding(10.dp))
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

                Text(text = state.fixed_salary+ stringResource(id = R.string.Egyptian_Currency), Modifier.padding(10.dp))



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
                .offset(x = (15).dp),
            loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = ""
                )
            }
        )
    }
}

@Preview
@Composable
fun FavoriteItemPrev() {
    //   FavoriteItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.StartToEnd) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = {
                DeleteBackground(swipeDismissState = state)
            },
            content = { content(item) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: SwipeToDismissBoxState
) {
    val color = if (swipeDismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier            .fillMaxSize() .padding(top=40.dp, bottom = 40.dp)

            .background(color)
            ,        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}

