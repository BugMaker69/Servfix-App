package com.example.graduationproject.presentation.viewprofile
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.Provider
import com.example.graduationproject.presentation.search_for_provider.FindProviderViewModel
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.White


@Composable
fun ViewProfileScreen(modifier: Modifier){
    val viewProfileViewModel: ViewProfileViewModel = viewModel()

    Box(modifier =Modifier.fillMaxSize()){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row (
                Modifier
                    .padding(20.dp)
                    .fillMaxSize(), horizontalArrangement = Arrangement.Center){
                UpperScreen(viewProfileViewModel.profile.value)

            }
            Text(text = viewProfileViewModel.profile.value.username.toString(), fontSize = 30.sp)

            Text(text = viewProfileViewModel.profile.value.profession.toString(), fontSize = 20.sp)
            Row {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription ="location",modifier=Modifier.size(20.dp) )
                Text(text = viewProfileViewModel.profile.value.city.toString(), fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.padding(5.dp))


            Row (Modifier.fillMaxSize(),Arrangement.Center){
                Button(modifier=Modifier.weight(1f),onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.call), fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Icon(imageVector = Icons.Filled.Phone, contentDescription ="phone", tint = White )

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(modifier=Modifier.weight(1f),onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.chat), fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(4.dp))

                    Icon(imageVector = Icons.Filled.Chat, contentDescription = "chat")
                }


            }

            when(viewProfileViewModel.isFavourite.value){
                true->        Icon(imageVector = Icons.Filled.Favorite,contentDescription = "favourite",tint= DarkBlue, modifier = Modifier.size(40.dp))
                false->        Icon(imageVector = Icons.Outlined.FavoriteBorder,contentDescription = "favourite",tint= DarkBlue, modifier = Modifier.clickable {
                    viewProfileViewModel.addToFavourite()
                })

            }

            WorkList()



        }

    }


}



@Composable
fun WorkList(){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.fillMaxWidth().height(700.dp)
        .padding(20.dp)
       )  {
        items(20){
            Image(contentScale = ContentScale.FillBounds, painter = painterResource(id = R.drawable.rr), contentDescription = "",modifier= Modifier
                .padding(5.dp)
                .size(200.dp))
        }
    }

}
@Composable
fun UpperScreen(state: Provider) {
    //Card(Modifier.fillMaxSize(), shape = RectangleShape) {

    SubcomposeAsyncImage(
        model = state.image,
        clipToBounds = true,
        contentDescription = "",
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape),
        loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
        error = {
            Image(
                painter = painterResource(id = R.drawable.ic_become),
                contentDescription = "",
                modifier= Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                     , contentScale = ContentScale.Inside)

        }
    )

  //  }
}
//
//@Preview
//@Composable
//fun testing(){
//    ViewProfileScreen(Modifier.padding(8.dp),ViewProfileViewModel())
//}