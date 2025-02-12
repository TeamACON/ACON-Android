package com.acon.core.utils.feature.action

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun BackOnPressed(
    toastMessage: String,
    context: Context
) {
    var backPressedTime by remember { mutableLongStateOf(0L) }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime <= 400L) {
            (context as? Activity)?.finish()
        } else {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    }
}