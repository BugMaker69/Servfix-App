package com.example.graduationproject.presentation.search_for_provider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.R
import com.example.graduationproject.data.ServiceProviderCard
import com.example.graduationproject.presentation.common.HomeTopBar
import com.example.graduationproject.presentation.common.ScreensTemplate

@Composable
fun FindProvider(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit,
    onMessageClick: () -> Unit,
) {
    val findProviderViewModel: FindProviderViewModel = viewModel()
    val names by findProviderViewModel.serviceProviders.collectAsState()
    val searchText by findProviderViewModel.searchText.collectAsState()
    ScreensTemplate(topBar = {
        HomeTopBar(
            onNotificationClick = { onNotificationClick() },
            onMessageClick = { onMessageClick() })
    }
    ) {

        TextField(
            value = searchText,
            onValueChange = findProviderViewModel::onSearchTextChange,
            modifier = modifier.fillMaxWidth().padding(top=60.dp),
            placeholder = {
                Text(
                    text = "search"
                )
            })
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, top = 116.dp)
        ) {
            items(names) {
                ProviderItem(modifier, it)
            }

        }
//            LazyVerticalGrid(columns = GridCells.Fixed(2) ){
//        items (20){
//        ProviderItem(modifier,findProviderViewModel.testState.get(0))
//       }
  //}

    }

}

@Composable
fun ProviderItem(modifier: Modifier, state: ServiceProviderCard) {
    Card(
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
                    Text(text = state.name, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
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
                    Text(state.location, color = Gray, modifier = Modifier.weight(1f))
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
                        state.rate.toString(), modifier = Modifier.weight(0.3f)
                    )
                    Divider(
                        color = Gray, modifier = modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .width(1.dp)
                            .height(15.dp)

                    )
                    Icon(imageVector = Icons.Filled.Handshake, contentDescription = "")
                    Text(
                        text = state.transactionsNum.toString(),

                        modifier = modifier.weight(0.7f)
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = state.fee.toString() + " " + stringResource(id = R.string.Egyptian_Currency),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.feeType,
                        color = Gray,
                        fontSize = 13.sp,
                        modifier = modifier.padding(start = 7.dp, top = 7.dp)
                    )


                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProviderItemPrev() {
    //   ProviderItem(modifier = Modifier,SeriveProviderCard())
    FindProvider(Modifier, {}, {})
}