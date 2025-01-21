package com.acon.data.api.remote

import com.acon.data.dto.request.PostOnboardingResultRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingApi {

    @POST("/api/v1/member/preference")
    //@POST("/api/v1/member/acorn")
    suspend fun postOnboardingResult(
        @Body body: PostOnboardingResultRequest
    ) : Response<Unit>
}