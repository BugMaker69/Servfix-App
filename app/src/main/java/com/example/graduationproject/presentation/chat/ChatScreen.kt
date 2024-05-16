package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.data.Chat
import com.example.graduationproject.ui.theme.DarkWhite
import com.example.graduationproject.ui.theme.LightBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(vm: ChatViewModel) {
    var textState by remember { mutableStateOf("") }
    val lista = vm.chat.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
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
                    MessageRow(chat = it, vm.retrievedId)
                }
            }
            UserInputField(
                textState,
                onValueChange = { textState = it },
                onSend = { /*vm.sendMessage(textState) */ })
        }
    }
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
            .padding(8.dp)
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
fun MessageRow(chat: Chat, receiver: Int) {
    Log.d("momo", "MessageRow: ${chat.sender} and ${chat.recipient}")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (chat.sender != receiver) Arrangement.End else Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(if (chat.sender != receiver) LightBlue else DarkWhite), // Adjust this color to match WhatsApp's gray
            modifier = Modifier.padding(4.dp)
        ) {
            Column {
                Text(
                    text = chat.content,
                    modifier = Modifier.padding(8.dp),
                    color = Color.Black
                )
                Text(
                    text = chat.timestamp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}


//            Row(Modifier.fillMaxSize(), Arrangement.Center) {
//                Button(modifier = Modifier.weight(1f), onClick = {
//                    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
//                        data = android.net.Uri.parse("tel:${viewProfileViewModel.profile.value.provider!!.phone}")
//                    }
//                    context.startActivity(dialIntent)
//
//
//                }) {
//                    Text(text = stringResource(id = R.string.call), fontSize = 20.sp)
//                    Spacer(modifier = Modifier.padding(4.dp))
//                    Icon(
//                        imageVector = Icons.Filled.Phone,
//                        contentDescription = "phone",
//                        tint = White
//                    )
//
//                }

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