@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.graduationproject.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.graduationproject.R
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.presentation.accountinfo.ProviderAccountInfoViewModel
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.ui.theme.DarkBlue


//  TODO HARD WRITTEN TEXT,IMAGE NEED TO BE DYNAMIC AND CREATE VIEWMODEL


@Composable
fun AddWorkToProfileItem(
    modifier: Modifier = Modifier,
    onAddWorkToProfileItemOpenPhoto: () -> Unit,
    onDeleteIconClick: () -> Unit,
    image: Uri

) {

    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable { onAddWorkToProfileItemOpenPhoto() }
//            .clip(RoundedCornerShape(16.dp))
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onDeleteIconClick() },
            imageVector = Icons.Filled.Cancel,
            contentDescription = ""
        )

        SubcomposeAsyncImage(
            model = image,
            clipToBounds = true,
            contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_become),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape), contentScale = ContentScale.Inside
                )

            }
        )

        /*        Image(
                    //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                    painter = rememberImagePainter(data = Base + image),
                    contentDescription = "",
        //            contentScale = ContentScale.Crop,
                    modifier = Modifier.align(Alignment.Center)
        //                modifier = Modifier.size(300.dp),
                )*/
    }

}


@Composable
fun AddNeWorkToProfileItem(
    modifier: Modifier = Modifier,
    providerAccountInfoViewModel:ProviderAccountInfoViewModel
) {


    val context = LocalContext.current


    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            providerAccountInfoViewModel.handleGalleryResult(context, uris)
        }

    Box(
        modifier = Modifier
            .size(150.dp)
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
                .clickable {
//                    if (serviceViewModel.imageMap.size != serviceViewModel.maxImages) {
                    providerAccountInfoViewModel.startLoading()
                    galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                    }
                }

        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }

    }

}


@Composable
fun AddNeWorkToProfileItems(
    modifier: Modifier = Modifier,
    onAddWorkToProfileItemOpenPhoto: () -> Unit,
    onSaveWorkClick: () -> Unit,
//    onDeleteIconClick: () -> Unit,
    providerAccountInfoViewModel: ProviderAccountInfoViewModel,
//    viewModel: NotificationViewModel,
    getWorks: List<GetWorksItem>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            AddNeWorkToProfileItem(providerAccountInfoViewModel = providerAccountInfoViewModel)
        }
        items(providerAccountInfoViewModel.imageMap.entries.toList()) { (id,uri) ->
            AddWorkToProfileItem(
                onAddWorkToProfileItemOpenPhoto = onAddWorkToProfileItemOpenPhoto,
                image = uri,
                onDeleteIconClick = { providerAccountInfoViewModel.removeImage(id) }
            )
        }
        item(span = { GridItemSpan(3) }) {
            CustomButtonAndText(
                text = R.string.send,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                backgroundColor = DarkBlue,
                contentColor = Color.White,
                onClick = onSaveWorkClick
            )
        }
        /*        items(getWorks) { item ->
                    AddWorkToProfileItem(
                        onAddWorkToProfileItemOpenPhoto = onAddWorkToProfileItemOpenPhoto,
                        image = item.image
                    )
                }*/
    }

}


@Composable
fun SeeWork(
    modifier: Modifier = Modifier,
    onBackIconClick: () -> Unit,
//    image:String,
    onDeleteIconClick: (Int) -> Unit,
//    id: Int,
    seeWorkViewModel: SeeWorkViewModel
) {

    var showDialog by remember { mutableStateOf(false) }


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
                    .clickable { onBackIconClick() }

            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = stringResource(
                        R.string.go_back
                    )
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { showDialog = true }

            ) {

                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = Color.Red,
                    contentDescription = stringResource(R.string.delete)
                )
            }

            if (showDialog) {
                DeleteImageDialog(
                    onDismissButtonClick = { showDialog = false },
                    onConfirmButtonClick = {
                        onDeleteIconClick(seeWorkViewModel.imageId)
                        showDialog = false
//                        serviceViewModel.deleteWork(serviceViewModel.imageId)
                    }
                )
            }

        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        ) {

            SubcomposeAsyncImage(
                model = Constant.BASE_URL + seeWorkViewModel.imageWork,
                clipToBounds = true,
                contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Inside,
                loading = { CircularProgressIndicator(Modifier.wrapContentSize()) },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_become),
                        contentDescription = "",
                        modifier = Modifier
                            .size(200.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Inside
                    )
                }
            )

/*            Image(
                //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                painter = painterResource(id = R.drawable.forgot_password),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )*/
        }

    }

}


@Composable
fun DeleteImageDialog(
    modifier: Modifier = Modifier,
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,

    ) {

    CustomDialog(
        title = {
            Text(
                text = stringResource(R.string.confirmation),
                modifier = Modifier,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        },
        text = { Text(text = "Are you sure you want to Delete It?") },
        dismissButtonText = stringResource(R.string.cancel),
        confirmButtonText = "Delete",
        onDismissButtonClick = onDismissButtonClick,
        onConfirmButtonClick = onConfirmButtonClick,
    )

}


/*

@Preview(showBackground = true)
@Composable
fun NotifiPostItemPreview() {
    NotifiPostItem(Modifier, {})
}


@Preview(showBackground = true)
@Composable
fun NotifiPostItemDetailPreview() {
    NotifiPostItemDetail(onNotifiPostItemDetailToOpenIt = {})
}


@Preview(showBackground = true)
@Composable
fun AddWorkToProfileItemPreview() {
    AddWorkToProfileItem(Modifier, {})
}


@Preview(showBackground = true)
@Composable
fun AddNeWorkToProfileItemPreview() {
    AddNeWorkToProfileItem()
}


@Preview(showBackground = true)
@Composable
fun SeeWorkPreview() {
    SeeWork(Modifier, {}, {})
}


@Preview(showBackground = true)
@Composable
fun DeleteImageDialogPreview() {
    DeleteImageDialog(Modifier, {}, {})
}


@Preview(showBackground = true)
@Composable
fun AddNeWorkToProfileItemsPreview() {
    AddNeWorkToProfileItems(Modifier, {})
}


@Preview(showBackground = true)
@Composable
fun NotifiPostItemsPreview() {
    NotifiPostItems(Modifier, {})
}
*/
