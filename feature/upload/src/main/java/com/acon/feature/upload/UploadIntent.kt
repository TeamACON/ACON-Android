package com.acon.feature.upload

sealed interface UploadIntent {
    data object SelectDotori : UploadIntent
    data object DeselectDotori : UploadIntent
    data object NavigateBack : UploadIntent
    data object UploadDotori : UploadIntent
}
