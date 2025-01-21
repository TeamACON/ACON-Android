package com.acon.feature.upload

import com.acon.domain.model.upload.SpotListItem

sealed interface UploadIntent {
    data class SelectLocation(val location: SpotListItem) : UploadIntent
    data class SelectDotori(val index: Int) : UploadIntent
    data class DeselectDotori(val index: Int) : UploadIntent
    data object NavigateBack : UploadIntent
    data object OnNextStep : UploadIntent
    data object UploadDotori : UploadIntent
    data class LoadSuggestions(
        val latitude: Double,
        val longitude: Double
    ) : UploadIntent
}
