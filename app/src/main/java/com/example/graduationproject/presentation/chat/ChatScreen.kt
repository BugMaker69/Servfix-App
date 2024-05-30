package com.example.graduationproject.presentation.chat

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.Chat
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.presentation.favourite.FavouriteAlertDialog
import com.example.graduationproject.presentation.search_for_provider.RatingBar
import com.example.graduationproject.ui.theme.DarkWhite
import com.example.graduationproject.ui.theme.LightBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(vm: ChatViewModel,modifier: Modifier,userType: String) {
    var textState by remember { mutableStateOf("") }
    val lista = vm.chat.collectAsState()
    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SubcomposeAsyncImage(
                            model = vm.retrievedImage,
                            clipToBounds = true,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(45.dp),
                            loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
                            error = {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_become),
                                    contentDescription = ""
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = vm.retrievedName)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Default.Call, contentDescription = "call ",modifier=Modifier.clickable {
                            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                               // data = android.net.Uri.parse("tel:${viewProfileViewModel.profile.value.provider!!.phone}")
                            }
                            context.startActivity(dialIntent)

                        })
                        if (userType==UserType.OwnerPerson.name){
                            RatingBar(rating = vm.rating, onRateClick = {
                                    starRating ->
                                vm.rating = starRating
                                vm.addReview()
                                vm.terminateChat()

                            })
                        }

                    }

                }

            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
                if(vm.showAlert){
                    ChatAlert(onDismissClick = { vm.showAlert=false }) {
                        vm.deleteMessage()
                        vm.showAlert=false

                    }
                }


                Image(
                    painter = painterResource(id = R.drawable.oq),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Column {

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        reverseLayout = true
                    ) {
                        items(lista.value) {
                            MessageRow(chat = it, vm.retrievedId){
                                vm.deletedMessageId=it.id
                                vm.showAlert=true
                            }
                        }
                    }
                    UserInputField(
                        textState,
                        onValueChange = { textState = it },
                        onSend = {
                            if(textState.isNotBlank()){
                                vm.addMessage(message = textState, id = vm.retrievedId)
                                textState = ""
                            }

                        })
                }
            }
        })
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputField(
    text: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(Color(0xFFECE5DD)) // Adjust this color to match WhatsApp's gray
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            placeholder = { Text("Type a message") },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )
        IconButton(onClick = onSend) {
            Icon(Icons.Filled.Send, contentDescription = "Send")
        }
    }
}

@Composable
fun MessageRow(chat: Chat, receiver: Int,onDeleteMessage:()->Unit) {
    Log.d("momo", "MessageRow: ${chat.sender} and ${chat.recipient}")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

        ,
        horizontalArrangement = if (chat.sender != receiver) Arrangement.End else Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(if (chat.sender != receiver) LightBlue else DarkWhite), // Adjust this color to match WhatsApp's gray
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    onDeleteMessage()
                }
        ) {
            Column {
                Text(
                    text = chat.content,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.End)
                        .padding(8.dp),
                    color = Color.Black, fontSize = 20.sp
                )
                Text(
                    text = chat.timestamp,
                    textAlign = TextAlign.End,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 5.dp)
                )
            }
        }
    }
}

@Composable
fun ChatAlert(onDismissClick:()->Unit,onConfirmClick:()->Unit){
    AlertDialog(
        text = { Text(text = stringResource(id = R.string.delete_message)) },
        onDismissRequest = {
            onDismissClick()

        },
        confirmButton = {
            Button(onClick = { onConfirmClick() }) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },

        dismissButton = {
            Button(onClick = { onDismissClick() }) {
                Text(text = stringResource(id = R.string.cancel))

            }

        })
}

//@Preview(showBackground = true)
//@Composable
//fun ChatScreenPreview() {
//    val sampleMessages = listOf(
//        Message(1, "Hello!", false),
//        Message(2, "Hi! How are you?", true),
//        Message(3, "I'm good, thanks for asking.", false)
//    )
//    ChatScreen(sampleMessages)
//}
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            TextField(
//                value = textState,
//                onValueChange = { textState = it },
//                modifier = Modifier.weight(1f)
//            )
//            Button(
//                onClick = { vm.btnClicked() },
//                modifier = Modifier.padding(start = 8.dp)
//            ) {
//                Text("Send")
//            }
//        }