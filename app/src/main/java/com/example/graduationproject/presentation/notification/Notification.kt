package com.example.graduationproject.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.graduationproject.R
import com.example.graduationproject.data.AllNotificationItem
import com.example.graduationproject.data.GetPostDataItem
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomTopAppBar
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onNotificationItemClick: (Int) -> Unit,

    //notificationDescription: String,
    allNotification: List<AllNotificationItem>


) {


    NotificationItems(
        modifier = modifier,
        onNotificationItemClick = { onNotificationItemClick(it) },
        allNotification = allNotification
    )
}


@Composable
fun NotificationItems(
    modifier: Modifier = Modifier,
    onNotificationItemClick: (Int) -> Unit,
//    notificationDescription: String,
    allNotification: List<AllNotificationItem>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(allNotification) { item ->
            NotificationItem(
                onNotificationItemClick = { onNotificationItemClick(item.post) },
                allNotificationItem = item
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationTopBar(
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit
) {
    CustomTopAppBar(
        title = "Notification",
        navigationIcon = {
            IconButton(onClick = onBackButtonOnTopNavBar) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
            }
        }, scrollBarBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    onNotificationItemClick: () -> Unit,
//    notificationDescription: String,
    allNotificationItem: AllNotificationItem

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onNotificationItemClick,
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
//                    .border(width = .21.dp, shape = RoundedCornerShape(32.dp), color = Color.Black)
            ) {
                Image(
                    //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp),
                )
            }
            Text(text = allNotificationItem.message)
        }

    }


}


@Composable
fun NotificationDetails(
    modifier: Modifier = Modifier,
    onNotifiPostItemDetailToOpenIt: () -> Unit,
    onAcceptButtonClick: () -> Unit,
    getPostDataItem: GetPostDataItem
) {

    val Base = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { onNotifiPostItemDetailToOpenIt() }

        ) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
        }


        Text(
            text = getPostDataItem.problem_description,
            modifier = Modifier.padding(8.dp),
            style = TextStyle(textAlign = TextAlign.Start)
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        ) {
            Image(
                //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                painter = rememberImagePainter(data = Base + getPostDataItem.image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
        ) {
/*            CustomButtonAndText(
                text = R.string.reject,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
                modifier = Modifier.padding(end = 50.dp)
            )*/
            CustomButtonAndText(
                text = R.string.accept,
                onClick = onAcceptButtonClick,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
            )
        }

    }

}


//
//@Preview(showBackground = true)
//@Composable
//fun NotificationItemPreview() {
//    NotificationItem(
//        Modifier,
//        {},
//        notificationDescription = "said accepted your service and wants to solve your problem"
//    )
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun NotificationItemsPreview() {
//    NotificationItems(
//        Modifier,
//        {},
//        notificationDescription = "said accepted your service and wants to solve your problem"
//    )
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun NotificationScreenPreview() {
//    NotificationScreen(
//        Modifier,
//        {},
//        notificationDescription = "said accepted your service and wants to solve your problem"
//    )
//}
//









