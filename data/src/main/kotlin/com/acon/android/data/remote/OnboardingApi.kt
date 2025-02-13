package com.acon.android.data.remote

import com.acon.android.data.dto.request.PostOnboardingResultRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingApi {

    @POST("/api/v1/member/preference")
    suspend fun postOnboardingResult(
        @Body body: PostOnboardingResultRequest
    ) : Response<Unit>
}