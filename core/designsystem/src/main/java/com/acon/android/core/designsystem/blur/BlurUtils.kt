package com.acon.android.core.designsystem.blur

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun rememberHazeState() = remember {
    HazeState()
}

fun Modifier.defaultHazeEffect(
    hazeState: HazeState,
    tintColor: Color,
    blurRadius: Dp = 40.dp,
    alpha: Float = .4f,
    backgroundColor: Color = Color.Black
): Modifier {
    return this.then(
        Modifier.hazeEffect(
            state = hazeState, style = HazeStyle(
                backgroundColor = backgroundColor,
                tints = listOf(
                    HazeTint(
                        tintColor.copy(
                            alpha = alpha
                        )
                    )
                ),
                blurRadius = blurRadius,
                noiseFactor = HazeDefaults.noiseFactor,
            )
        )
    )

}

val LocalHazeState = staticCompositionLocalOf {
    HazeState()
}
