@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.graduationproject.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.graduationproject.R
import com.example.graduationproject.data.GetPostDataItem
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.presentation.common.CustomDialog
import com.example.graduationproject.presentation.notification.NotificationViewModel
import com.example.graduationproject.presentation.userservice.ServiceViewModel
import com.example.graduationproject.ui.theme.DarkBlue


//  TODO HARD WRITTEN TEXT,IMAGE NEED TO BE DYNAMIC AND CREATE VIEWMODEL

@Composable
fun NotifiPostItem(
    modifier: Modifier = Modifier,
    onNotifiPostItemClick: () -> Unit,
    getPostsForProviderItem: GetPostsForProviderItem
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(.6f)
            .padding(8.dp),
        onClick =  onNotifiPostItemClick ,

        ) {
        Text(
            text = getPostsForProviderItem.message,
            modifier = Modifier.padding(8.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(textAlign = TextAlign.Start)
        )
        Text(
            text = stringResource(R.string.see_more),
            modifier = Modifier
                .padding(end = 8.dp, bottom = 8.dp)
                .align(Alignment.End),
            style = TextStyle(textAlign = TextAlign.Start, color = Color.Black.copy(alpha = .4f))
        )
    }

}


@Composable
fun NotifiPostItems(
    modifier: Modifier = Modifier,
    onNotifiPostItemClick: (Int) -> Unit,
    getPostsForProvider: List<GetPostsForProviderItem>
) {
    LazyColumn(

    ) {
        items(getPostsForProvider) { item ->
            NotifiPostItem(
                modifier = Modifier,
                onNotifiPostItemClick = { onNotifiPostItemClick(item.id) },
                getPostsForProviderItem = item
            )
        }
    }
}


@Composable
fun NotifiPostItemDetailByID(
    modifier: Modifier = Modifier,
    onNotifiPostItemDetailToOpenIt: () -> Unit,
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

    }

}



@Composable
fun NotifiPostItemDetail(
    modifier: Modifier = Modifier,
    onNotifiPostItemDetailToOpenIt: () -> Unit,
    getPostsForProviderItem: GetPostsForProviderItem
) {

    val Base = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

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
                .clickable { onNotifiPostItemDetailToOpenIt() }

        ) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
        }


        Text(
            text = getPostsForProviderItem.message,
            modifier = Modifier.padding(8.dp),
            style = TextStyle(textAlign = TextAlign.Start)
        )
        Box(
            modifier = Modifier
//                .size(300.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
        ) {
            Image(
                //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
                painter = rememberImagePainter(data = Base + getPostsForProviderItem.image),
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

    }

}


@Composable
fun AddWorkToProfileItem(
    modifier: Modifier = Modifier,
    onAddWorkToProfileItemOpenPhoto: () -> Unit,
    image:String

) {

    val Base = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable { onAddWorkToProfileItemOpenPhoto() }
//            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            //  TODO Image Want To Be Dynamic I don't Know yet if i will use painter or ImageVector or What?
            painter = rememberImagePainter(data = Base + image),
            contentDescription = "",
//            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.Center)
//                modifier = Modifier.size(300.dp),
        )
    }

}


@Composable
fun AddNeWorkToProfileItem(
    modifier: Modifier = Modifier,
    serviceViewModel: ServiceViewModel = viewModel()
) {


    val context = LocalContext.current


    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            serviceViewModel.imageUri = uri
        }
    /*    val galleryLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris ->
                serviceViewModel.handleGalleryResult(context, uris)
            }*/

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
                    serviceViewModel.startLoading()
                    galleryLauncher.launch("image/*")
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
//    serviceViewModel: ServiceViewModel = viewModel()
//    viewModel: NotificationViewModel,
    getWorks:List<GetWorksItem>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            AddNeWorkToProfileItem()
        }
        items(getWorks) {item->
            AddWorkToProfileItem(onAddWorkToProfileItemOpenPhoto = onAddWorkToProfileItemOpenPhoto, image = item.image)
        }
    }

}


@Composable
fun SeeWork(
    modifier: Modifier = Modifier,
    onBackIconClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
    id:Int,
    viewModel: NotificationViewModel
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

            if (showDialog){
                DeleteImageDialog(
                    onDismissButtonClick = { showDialog = false },
                    onConfirmButtonClick = {
                        viewModel.deleteWork(id)
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
