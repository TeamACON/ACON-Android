package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.fengdai.compose.pulltorefresh.PullToRefreshState

@Composable
fun SpotListPullToRefreshIndicator(
    refreshTriggerDistance: Dp,
    state: PullToRefreshState
) {
    val refreshTriggerPx = with(LocalDensity.current) { refreshTriggerDistance.toPx() }
    val indicatorSize = 36.dp
    val indicatorHeightPx = with(LocalDensity.current) { indicatorSize.toPx() }
    val rotation: Float
    val scaleFraction: Float
    val alphaFraction: Float
    if (!state.isRefreshing) {
        val progress = (state.contentOffset / refreshTriggerPx.coerceAtLeast(1f))
            .coerceIn(0f, 1f)
        rotation = progress * 180
        scaleFraction = LinearOutSlowInEasing.transform(progress)
        alphaFraction = progress
    } else {
        val transition = rememberInfiniteTransition()
        rotation = transition.animateValue(
            0f,
            1f,
            Float.VectorConverter,
            infiniteRepeatable(
                animation = tween(
                    durationMillis = 1332, // 1 and 1/3 second
                    easing = LinearEasing
                )
            )
        ).value * 360
        scaleFraction = 1f
        alphaFraction = 1f
    }
    CircularProgressIndicator(
        modifier = Modifier
            .graphicsLayer {
                translationY = (state.contentOffset - indicatorHeightPx) / 2f
                scaleX = scaleFraction
                scaleY = scaleFraction
                alpha = alphaFraction
            }
            .size(indicatorSize)
            .statusBarsPadding()
            .padding(top = 16.dp)
    )
}