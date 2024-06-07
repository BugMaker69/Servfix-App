import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.presentation.chat.ChatContactViewModel
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun ChatContactScreen(modifier: Modifier, vm: ChatContactViewModel,onChatClick: (Int,String,String,Int,String) -> Unit) {

    val list = vm.chatListItems.collectAsState()
    Log.d("donee", "ChatContactScreen: ${list.value.size}")

    Log.d("ChatScreen999", "ChatScreen: ${list.value.isEmpty()}")
    Log.d("ChatScreen444", "ChatScreen: ${list.value.isNotEmpty()}")
    Log.d("ChatScreen555", "ChatScreen: ${list.value}")
    if (list.value.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "There is no Contacts Yet", color = Color.Black)
        }
    } else {
        LazyColumn(modifier.fillMaxSize()) {
            items(list.value) { item ->
                ChatContactItem(
                    terminateId = item.terminate_id,
                    id = item.id,
                    name = item.name,
                    image = item.image ?: "empty",
                    lastMessage = item.content ?: "",
                    unseenCount = item.unseenMessages?.toIntOrNull() ?: 0,
                    onChatClick = onChatClick,
                    phone = item.phone.toString()
                )

            }
        }
    }
}



@Composable
fun ChatContactItem(phone:String, terminateId:Int, id: Int, name: String, image: String, lastMessage: String, unseenCount: Int, onChatClick: (Int, String, String, Int, String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                Log.d("popo", "ChatContactItem:$id ")
                Log.d("btx", "ChatContactItem: $image ")

                onChatClick(id, name, image, terminateId,phone)
            },
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RectangleShape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = image,
                clipToBounds = true,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp),
                loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_become),
                        contentDescription = ""
                    )
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lastMessage,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxHeight()
            ) {

                if (unseenCount > 0) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(24.dp)
                            .background(DarkBlue, CircleShape)
                    ) {
                        Text(
                            text = unseenCount.toString(),
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    val participants = listOf(
//        ChatParticipant("John Doe", "Hello!"),
//        ChatParticipant("Jane Smith", "How are you?")
//    )
    //   chatContactItem()
}
