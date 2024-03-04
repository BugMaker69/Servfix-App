package com.example.graduationproject.presentation.on_boarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.graduationproject.presentation.common.CustomButtonAndText
import com.example.graduationproject.R
import com.example.graduationproject.ui.theme.DarkBlue
import com.example.graduationproject.ui.theme.LightBlue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onNextClick: () -> Unit) {
    val vm: OnBoardingViewModel = viewModel()
    val pagerState = rememberPagerState {
        vm.animations.size
    }
    LaunchedEffect(pagerState.currentPage) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            vm.onPageChange(page)
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState,Modifier.weight(1f)) { position ->
            ContentPager(animations = vm.animations[position], text = vm.descriptin[position])
        }
        Spacer(modifier = Modifier.padding(10.dp))

            PageIndicator(
                pagecount = vm.animations.size,
                currentpage = pagerState.currentPage,
                modifier = Modifier.padding(60.dp)
            )


        if (pagerState.currentPage == 2) {
            CustomButtonAndText(
                modifier = Modifier
                    .size(width = 350.dp, height = 85.dp)
                    .padding(20.dp),
                text = R.string.next,
                shape = RoundedCornerShape(36.dp),

                backgroundColor = DarkBlue,
                contentColor = Color.White,
                fontSize = 20,
                fontWeight = FontWeight.Normal,
                onClick = onNextClick
            )
        }

    }

}


@Composable
fun ContentPager(animations: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animations))

        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            iterations = 1
        )

        Text(text = text, modifier = Modifier.padding(20.dp))
    }
}

@Composable
fun PageIndicator(pagecount: Int, currentpage: Int, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        repeat(pagecount) {
            IndicatorSingleDot(isSelected = it == currentpage)
        }

    }


}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 15.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) DarkBlue else LightBlue)

    )

}

