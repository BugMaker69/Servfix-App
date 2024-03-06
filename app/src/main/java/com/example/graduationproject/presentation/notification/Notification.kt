package com.example.graduationproject.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomTopAppBar
import com.example.graduationproject.presentation.common.ScreensTemplate


data class Notification(
    val userId: String,
    val notificationId: String,
    val userImage: Int,
    val notificationDescription: String,
    val isRead: Boolean,
)


@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onBackButtonOnTopNavBar: () -> Unit,
    onNotificationItemClick: () -> Unit,
    notificationDescription: String,
) {

    ScreensTemplate(
        topBar = { NotificationTopBar { onBackButtonOnTopNavBar() } },
    ) {
        NotificationItems(
            modifier = modifier.padding(it),
            onNotificationItemClick = onNotificationItemClick,
            notificationDescription = notificationDescription
        )
    }

}

@Composable
fun NotificationItems(
    modifier: Modifier = Modifier,
    onNotificationItemClick: () -> Unit,
    notificationDescription: String
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(15) {
            NotificationItem(
                onNotificationItemClick = onNotificationItemClick,
                notificationDescription = "said accepted your service and wants to solve your problem"
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
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    onNotificationItemClick: () -> Unit,
    notificationDescription: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onNotificationItemClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
//                    .border(width = .21.dp, shape = RoundedCornerShape(32.dp), color = Color.Black)
                    ,
            ) {
                Image(
                    //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp),
                )
            }
            Text(text = notificationDescription)
        }

    }


}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        Modifier,
        {},
        notificationDescription = "said accepted your service and wants to solve your problem"
    )
}


@Preview(showBackground = true)
@Composable
fun NotificationItemsPreview() {
    NotificationItems(
        Modifier,
        {},
        notificationDescription = "said accepted your service and wants to solve your problem"
    )
}


@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(
        Modifier,
        {},
        {},
        notificationDescription = "said accepted your service and wants to solve your problem"
    )
}










