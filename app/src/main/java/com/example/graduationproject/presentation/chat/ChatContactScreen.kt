import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.chat.ChatContactViewModel
import com.example.graduationproject.presentation.common.UserType


@Composable
fun ChatContactScreen(modifier: Modifier, vm: ChatContactViewModel, userType: String) {
    val chatListUsers = vm.chatListUsers.collectAsState().value
    val chatListProviders = vm.chatListProviders.collectAsState().value



    LazyColumn(modifier.fillMaxSize()) {
        when (userType) {
            UserType.OwnerPerson.name -> {
                items(chatListProviders) {
                    ChatContactItem(it.id, it.name, it.image ?: "")
                }

            }


            UserType.HirePerson.name -> {
                items(chatListUsers) {
                    ChatContactItem(id = it.id, name = it.name, image = it.image ?: "")
                }
            }
        }

    }
}


@Composable
fun ChatContactItem(id: Int, name: String, image: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RectangleShape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp),
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 2.dp)
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "check")
                    Text(
                        text = "try and try you will succeess don't stop please you  ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

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
