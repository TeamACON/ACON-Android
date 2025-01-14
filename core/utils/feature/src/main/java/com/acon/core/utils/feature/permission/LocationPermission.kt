package com.acon.core.utils.feature.permission

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 위치 권한을 확인하고 요청하는 컴포저블
 * @param onPermissionGranted 권한이 허용되었을 때 실행할 동작
 * TODO : 다이얼로그 컴포넌트 생성 후 수정
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckAndRequestLocationPermission(
    onPermissionGranted: () -> Unit
) {
    var trigger by rememberSaveable { mutableIntStateOf(0) }
    var showPermissionDialog by rememberSaveable { mutableStateOf(false) }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
        onPermissionsResult = { permMap ->
            if (permMap[Manifest.permission.ACCESS_FINE_LOCATION] == false) {
                trigger = (trigger + 1).coerceAtMost(2)
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permMap ->
        if (permMap[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            onPermissionGranted()
            showPermissionDialog = false
        }
    }

//    if (showPermissionDialog)
//        AconConfirmDialog(...)

    LaunchedEffect(trigger) {
        withContext(Dispatchers.Main.immediate) {
            if (locationPermissionState.allPermissionsGranted) {
                onPermissionGranted()
            } else {
                if (locationPermissionState.shouldShowRationale) {
                    showPermissionDialog = true
                    // TODO : 다이얼로그의 버튼 클릭 시 아래 코드 실행
                    // permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
                } else {
                    locationPermissionState.launchMultiplePermissionRequest()
                }
            }
        }
    }
}