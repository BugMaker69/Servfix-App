

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.R

data class ChatParticipant(val name: String, val lastMessage: String)

@Composable
fun ChatContactScreen() {
    data class x(val name:String,val text:String)
val list= listOf<x>(x("AhmedRamadan12", "خلاص يا معلم اتفقنا بكرة إن شاء الله نتقابل اوكى فى اى مكان عند البنزينة ماشى"))
     LazyColumn(Modifier.fillMaxSize()){
         items(20){
             chatContactItem()
         }
     }
}
@Composable
fun chatContactItem(){
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
                    //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp),
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Text(text = "Ahmed Ramadan", fontSize = 20.sp, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.padding(top=6.dp))
Row(
    Modifier
        .fillMaxWidth()
        .padding(end = 2.dp)){
    Icon(imageVector =     Icons.Default.Done, contentDescription = "check")
    Text(text = "try and try you will succeess don't stop please you  ",fontSize=15.sp,fontWeight=FontWeight.Light,modifier=Modifier.fillMaxWidth(), maxLines = 1, overflow = TextOverflow.Ellipsis)

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
    chatContactItem()
}
