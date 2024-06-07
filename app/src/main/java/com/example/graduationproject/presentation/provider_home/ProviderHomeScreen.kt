package com.example.graduationproject.presentation.provider_home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graduationproject.PullToRefreshLazyColumn
import com.example.graduationproject.R
import com.example.graduationproject.data.GetPostsForProviderItem


@Composable
fun PostItem(
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
            modifier = Modifier.padding(16.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(textAlign = TextAlign.Start, fontSize = 18.sp)
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
    viewModel: ProviderHomeViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()

    PullToRefreshLazyColumn(modifier=modifier.fillMaxSize(),items =viewModel.getPostsForProvider , content = {item->

        PostItem(
            onPostClick = { onNotifiPostItemClick(item.id) },
            getPostsForProviderItem = item
        )
    }, isRefreshing = isLoading, onRefresh = {viewModel.loadStuff() })


    }


