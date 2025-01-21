package com.acon.data.datasource.remote

import com.acon.data.api.remote.UploadApi
import com.acon.data.dto.response.upload.UploadGetDotoriResponse
import com.acon.data.dto.response.upload.UploadGetKeyWordResponse
import com.acon.data.dto.response.upload.UploadGetSuggestionsResponse
import javax.inject.Inject

class UploadRemoteDataSource @Inject constructor(
    private val uploadApi: UploadApi
) {
    suspend fun getDotoriCount(): UploadGetDotoriResponse {
        return uploadApi.getDotoriCount()
    }

    suspend fun getKeyWord(keyword: String): UploadGetKeyWordResponse {
        return uploadApi.getKeyWord(keyword)
    }

    suspend fun getSuggestions(
        latitude: Double,
        longitude: Double
    ): UploadGetSuggestionsResponse {
        return uploadApi.getSuggestions(latitude, longitude)
    }
}
