package com.acon.feature.upload

sealed interface UploadIntent {
    data class SelectDotori(val index: Int) : UploadIntent
    data object DeselectDotori : UploadIntent
    data object NavigateBack : UploadIntent
    data object UploadDotori : UploadIntent
}
