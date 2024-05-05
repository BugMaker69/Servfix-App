package com.example.graduationproject.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.6f)
)
@Composable
fun ShimmerAnimation(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    gradientWidth: Float,
    content: @Composable() () -> Unit
) {
    val brush = Brush.linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    content()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush)
    )
}
@Composable
fun ShimmerEffect(
    colors: List<Color>,
    content: @Composable() () -> Unit
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, delayMillis = 500, easing = LinearEasing)
        ), label = ""
    )
    ShimmerAnimation(
        colors = colors,
        xShimmer = translateAnim,
        yShimmer = translateAnim,
        gradientWidth = 300f,
        content = content
    )
}
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        ShimmerEffect(colors = ShimmerColorShades) {
            // Your loading content here
        }
    }
}
