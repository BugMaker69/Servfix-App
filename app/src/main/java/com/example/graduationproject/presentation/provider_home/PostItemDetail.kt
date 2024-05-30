package com.example.graduationproject.presentation.provider_home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.ui.theme.DarkBlue


@Composable
fun PostItemDetail(
    modifier: Modifier,
    onNotifiPostItemDetailToOpenIt: () -> Unit,
    onAcceptButtonClickForSpecificProvider: () -> Unit,
    onRejectButtonClickForSpecificProvider: () -> Unit,
    viewModel: PostDetailViewModel
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
//            .fillMaxHeight(.6f)
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

        Log.d("PostItemDetail", "PostItemDetail: ${viewModel.getPostById}")
        Text(
            text = viewModel.getPostById!!.problem_description,
            modifier = Modifier.padding(8.dp).padding(horizontal = 16.dp, vertical = 32.dp),
            style = TextStyle(textAlign = TextAlign.Start, fontSize = 22.sp)
        )
        Box(
            modifier = Modifier
//                .size(300.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        ) {

            SubcomposeAsyncImage(
                model = Constant.BASE_URL + viewModel.getPostById!!.image,
                clipToBounds = true,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(200.dp),
                loading = { CircularProgressIndicator(modifier.wrapContentSize()) },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_become),
                        contentDescription = ""
                    )
                }
            )

            /*            Image(
                            //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                            painter = rememberImagePainter(data = Base + viewModel.getPostById!!.image),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
            //                modifier = Modifier.size(300.dp),
                        )*/
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
                onClick = onRejectButtonClickForSpecificProvider,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
                modifier = Modifier.padding(end = 50.dp)
            )
            CustomButtonAndText(
                text = R.string.accept,
                onClick = onAcceptButtonClickForSpecificProvider,
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                alignment = Alignment.Center,
            )
        }

    }

}
