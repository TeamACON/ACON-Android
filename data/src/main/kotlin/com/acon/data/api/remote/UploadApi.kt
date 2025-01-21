package com.acon.data.api.remote

import com.acon.data.dto.response.upload.UploadGetDotoriResponse
import com.acon.data.dto.response.upload.UploadGetKeyWordResponse
import com.acon.data.dto.response.upload.UploadGetSuggestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UploadApi {
    @GET("/api/v1/member/acorn")
    suspend fun getDotoriCount(): UploadGetDotoriResponse

    @GET("/api/v1/spots/search")
    suspend fun getKeyWord(@Query("keyword") keyword: String): UploadGetKeyWordResponse

    @GET("/api/v1/search-suggestions")
    suspend fun getSuggestions(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): UploadGetSuggestionsResponse
}
