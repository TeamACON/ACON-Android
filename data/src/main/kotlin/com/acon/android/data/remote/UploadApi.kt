package com.acon.android.data.remote

import com.acon.android.data.dto.request.ReviewRequest
import com.acon.android.data.dto.response.upload.UploadGetDotoriResponse
import com.acon.android.data.dto.response.upload.UploadGetKeyWordResponse
import com.acon.android.data.dto.response.upload.UploadGetSpotVerifyResponse
import com.acon.android.data.dto.response.upload.UploadGetSuggestionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
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

    @GET("/api/v1/spot/verify")
    suspend fun getVerifySpotLocation(
        @Query("spotId") spotId: Long,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): UploadGetSpotVerifyResponse

    @POST("/api/v1/review")
    @Headers("Content-Type: application/json")
    suspend fun uploadPostReview(
        @Body request: ReviewRequest
    )
}
