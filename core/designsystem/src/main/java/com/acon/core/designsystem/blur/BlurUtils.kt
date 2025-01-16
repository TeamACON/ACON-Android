package com.acon.core.designsystem.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

fun Modifier.defaultHazeEffect(
    hazeState: HazeState,
    tintColor: Color,
    blurRadius: Dp = 60.dp
) = this.then(
        Modifier.hazeEffect(
            state = hazeState, style = HazeStyle(
                backgroundColor = Color.Black,
                tints = listOf(
                    HazeTint(
                        tintColor.copy(
                            alpha = .2f
                        )
                    )
                ),
                blurRadius = blurRadius,
                noiseFactor = HazeDefaults.noiseFactor,
            )
        )
    )
