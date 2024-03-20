package com.example.graduationproject.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun NotifiPostItem() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(.6f)
            .padding(8.dp),

        ) {
        Text(
            text = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello",
            modifier = Modifier.padding(8.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(textAlign = TextAlign.Start)
        )
        Text(
            text = "See More",
            modifier = Modifier
                .padding(end = 8.dp, bottom = 8.dp)
                .align(Alignment.End),
            style = TextStyle(textAlign = TextAlign.Start, color = Color.Black.copy(alpha = .4f))
        )
    }

}


@Composable
fun NotifiPostItemDetail() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(.6f)
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { Log.d("CustomUI", "CustomUI: Clicked") }

        ) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
        }

        /*
                IconButton(onClick = { */
        /*TODO*//*
 }) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Go Back")
        }
*/

        Text(
            text = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(textAlign = TextAlign.Start)
        )
        Box(
            modifier = Modifier
//                .size(300.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
//                    .border(width = .21.dp, shape = RoundedCornerShape(32.dp), color = Color.Black)
        ) {
            Image(
                //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                painter = painterResource(id = R.drawable.forgot_password),
                contentDescription = "",
                contentScale = ContentScale.Crop,
//                modifier = Modifier.size(300.dp),
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
//                .fillMaxWidth()
//            .fillMaxHeight(.6f)
                .padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButtonAndText(
                text = R.string.reject,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
                modifier = Modifier.padding(end = 50.dp)
            )
            CustomButtonAndText(
                text = R.string.accept,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
            )
        }

//        Image(painter = painterResource(id = R.drawable.forgot_password), contentDescription = "", modifier = Modifier)

    }

}


@Composable
fun AddWorkToProfileItem() {

    Box(
        modifier = Modifier
            .size(300.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
//                    .border(width = .21.dp, shape = RoundedCornerShape(32.dp), color = Color.Black)
    ) {
        Image(
            //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
            painter = painterResource(id = R.drawable.forgot_password),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.Center)
//                modifier = Modifier.size(300.dp),
        )
    }

}


@Composable
fun AddNeWorkToProfileItem() {

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            drawLine(
                start = Offset.Zero,
                end = Offset(size.width, 0f),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                strokeWidth = 2.dp.toPx(),
                color = DarkBlue,
            )

            drawLine(
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                strokeWidth = 2.dp.toPx(),
                color = DarkBlue,
            )

            drawLine(
                start = Offset(size.width, size.height),
                end = Offset(0f, size.height),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                strokeWidth = 2.dp.toPx(),
                color = DarkBlue,
            )

            drawLine(
                start = Offset(0f, size.height),
                end = Offset(0f, 0f),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                strokeWidth = 2.dp.toPx(),
                color = DarkBlue,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(8.dp))
                .background(DarkBlue)
                .clickable { Log.d("CustomUI", "CustomUI: Clicked") }

        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }

    }

}


@Composable
fun SeeWork() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { Log.d("CustomUI", "Go Back") }

            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { Log.d("CustomUI", "Delete") }

            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = Color.Red,
                    contentDescription = "Delete"
                )
            }

        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        ) {
            Image(
                //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                painter = painterResource(id = R.drawable.forgot_password),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }

    }

}


@Composable
fun DeleteImageDialog() {

    CustomDialog(
        title = {
            Text(
                text = "Confirmation",
                modifier = Modifier,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        },
        text = { Text(text = "Are you sure you want to Delete It?") },
        dismissButtonText = "Cancel",
        confirmButtonText = "Delete",
        onDismissButtonClick = {},
        onConfirmButtonClick = {},
    )

}


@Preview(showBackground = true)
@Composable
fun NotifiPostItemPreview() {
    NotifiPostItem()
}


@Preview(showBackground = true)
@Composable
fun NotifiPostItemDetailPreview() {
    NotifiPostItemDetail()
}


@Preview(showBackground = true)
@Composable
fun AddWorkToProfileItemPreview() {
    AddWorkToProfileItem()
}


@Preview(showBackground = true)
@Composable
fun AddNeWorkToProfileItemPreview() {
    AddNeWorkToProfileItem()
}


@Preview(showBackground = true)
@Composable
fun SeeWorkPreview() {
    SeeWork()
}


@Preview(showBackground = true)
@Composable
fun DeleteImageDialogPreview() {
    DeleteImageDialog()
}
