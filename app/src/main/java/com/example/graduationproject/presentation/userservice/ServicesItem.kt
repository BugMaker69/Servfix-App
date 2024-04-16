package com.example.graduationproject.presentation.userservice

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.Services
import com.example.graduationproject.presentation.common.CustomTextField
import com.example.graduationproject.ui.theme.LightBlue


@SuppressLint("SuspiciousIndentation")
@Composable
fun UserHomeScreen(
    modifier: Modifier = Modifier,
    onTextFieldClick: () -> Unit,
    onServiceItemClick: (Int,String) -> Unit,

) {
    val serviceViewModel: ServiceViewModel = viewModel()


    ServicesHomePage(
        vm = serviceViewModel,
        modifier = modifier,
        onTextFieldClick = onTextFieldClick,
        onServiceItemClick = onServiceItemClick
    )

}


@Composable
fun ServicesHomePage(
    modifier: Modifier = Modifier,
    vm: ServiceViewModel = ServiceViewModel(),
    onTextFieldClick: () -> Unit,
    onServiceItemClick: (Int,String) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) GridCells.Fixed(3)  else GridCells.Fixed(2)

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ShareProblemBar(onTextFieldClick)
        LazyVerticalGrid(
            columns = columns,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            items(vm.servicesState.value) {
                ServicesItem(
                    it,
                    Modifier,
                    onServiceItemClick = { onServiceItemClick(it.id,it.name) })
            }
        }
    }
}


@Composable
fun ServicesItem(
    service: Services,
    modifier: Modifier = Modifier,
    onServiceItemClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onServiceItemClick() }
            .padding(14.dp)
            .border(width = 2.dp, color = LightBlue, shape = RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .background(Color.White)
            ) {
                SubcomposeAsyncImage(
                    model = service.image,
                    contentDescription = service.description,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(600f / 300f),
                    loading = { CircularProgressIndicator(modifier.wrapContentSize())}
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = LightBlue)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = service.name, color = Color.White)
            }
        }
    }
}


@Composable
fun ShareProblemBar(
    onTextFieldClick: () -> Unit
) {

    var value by remember { mutableStateOf("") }

    CustomTextField(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onTextFieldClick()
                Log.d("Clicked", "ShareProblemBar: Clicked")
            }
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    onTextFieldClick()
                    Log.d("Clicked", "ShareProblemBar: gained focus")
                }
            }
            .padding(horizontal = 16.dp, vertical = 4.dp),
        fieldName = R.string.share_problem,
        fieldValue = value,
        onValueChange = { value = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
    )

}


//@Preview(showBackground = true)
//@Composable
//fun UserHomeScreenPreview() {
//    UserHomeScreen(Modifier, {}, {}, {}, {3,"electricity"}, {})
//}


@Preview(showBackground = true)
@Composable
fun ServicesHomePagePreview() {
    // ServicesHomePage(Modifier, {}, {})
}


@Preview(showBackground = true)
@Composable
fun ShareProblemBarPreview() {
    ShareProblemBar({})
}


@Preview(showBackground = true)
@Composable
fun ServicesItemPreview() {
    // ServicesItem(Modifier, R.drawable.ic_paint, R.string.login, {})
}
