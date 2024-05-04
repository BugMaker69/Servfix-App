package com.example.graduationproject.presentation.provider_home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.graduationproject.R
import com.example.graduationproject.data.GetPostsForProviderItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    onPostClick: () -> Unit,
    getPostsForProviderItem: GetPostsForProviderItem
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight(.6f)
            .padding(8.dp),
        onClick = onPostClick,

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
fun ProviderPostScreen(
    modifier: Modifier ,
    onNotifiPostItemClick: (Int) -> Unit,
//    getPostsForProvider: List<GetPostsForProviderItem>
    viewModel: ProviderHomeViewModel
) {
    LazyColumn(modifier = modifier) {
        items(viewModel.getPostsForProvider) { item ->
            PostItem(
                modifier = Modifier,
                onPostClick = { onNotifiPostItemClick(item.id) },
                getPostsForProviderItem = item
            )
        }
    }
}

