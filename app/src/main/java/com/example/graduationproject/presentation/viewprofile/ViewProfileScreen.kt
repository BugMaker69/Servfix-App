package com.example.graduationproject.presentation.viewprofile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.DarkWhite
import com.example.graduationproject.ui.theme.White


@Composable
fun ViewProfileScreen(modifier: Modifier,viewProfileViewModel: ViewProfileViewModel){
    Column(
        modifier = modifier.background(DarkWhite)
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = { viewProfileViewModel.getData() }) {
                Text(text = "click")
            }
            Text(
                text = viewProfileViewModel.name.value,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(horizontal = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

        }
//        Row (
//            Modifier
//                .padding(20.dp)
//                .fillMaxSize(), horizontalArrangement = Arrangement.Center){
//            UpperScreen()
//
//        }
//        Text(text = "Ahmed Ramadan", fontSize = 30.sp)
//
//        Text(text = "Electriacal", fontSize = 20.sp)
//        Row {
//            Icon(imageVector = Icons.Filled.LocationOn, contentDescription ="location",modifier=Modifier.size(20.dp) )
//            Text(text = "Alexandria", fontSize = 13.sp)
//        }
//        Spacer(modifier = Modifier.padding(5.dp))
//
//
//        Row (Modifier.fillMaxSize(),Arrangement.Center){
//            Button(modifier=Modifier.weight(1f),onClick = { /*TODO*/ }) {
//                Text(text = stringResource(id = R.string.call), fontSize = 20.sp)
//                Spacer(modifier = Modifier.padding(4.dp))
//                Icon(imageVector = Icons.Filled.Phone, contentDescription ="phone", tint = White )
//
//            }
//            Spacer(modifier = Modifier.padding(10.dp))
//            Button(modifier=Modifier.weight(1f),onClick = { /*TODO*/ }) {
//                Text(text = stringResource(id = R.string.chat), fontSize = 20.sp)
//                Spacer(modifier = Modifier.padding(4.dp))
//
//                Icon(imageVector = Icons.Filled.Chat, contentDescription = "chat")
//            }
//
//
//        }
//        val x=2
//        when(x){
//            3->        Icon(imageVector = Icons.Filled.Favorite,contentDescription = "favourite",tint= DarkBlue, modifier = Modifier.size(40.dp))
//2->        Icon(imageVector = Icons.Outlined.FavoriteBorder,contentDescription = "favourite",tint= DarkBlue)
//
//        }
//
//
//
//    }
//        workList()
//


    }


}
@Composable
fun workList(){
    LazyVerticalGrid(GridCells.Fixed(2),modifier = Modifier.padding(20.dp)
        .fillMaxSize()
       )  {
        items(50){
            Image(contentScale = ContentScale.FillBounds, painter = painterResource(id = R.drawable.rr), contentDescription = "",modifier=Modifier.padding(5.dp).size(200.dp))
        }
    }

}
@Composable
fun UpperScreen(){
    //Card(Modifier.fillMaxSize(), shape = RectangleShape) {
        Image(painter = painterResource(id = R.drawable.ic_hire), contentDescription ="person",modifier= Modifier
            .size(130.dp)
            .padding(5.dp)
            .clip(CircleShape) , contentScale = ContentScale.Inside)

  //  }
}
//
//@Preview
//@Composable
//fun testing(){
//    ViewProfileScreen(Modifier.padding(8.dp),ViewProfileViewModel())
//}