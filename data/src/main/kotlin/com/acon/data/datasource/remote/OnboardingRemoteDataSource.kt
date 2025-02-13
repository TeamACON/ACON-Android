package com.acon.data.datasource.remote

import com.acon.data.api.remote.OnboardingApi
import com.acon.data.dto.request.PostOnboardingResultRequest
import retrofit2.Response
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val onboardingApi: OnboardingApi
) {

    suspend fun postResult(request: PostOnboardingResultRequest): Response<Unit> {
        return onboardingApi.postOnboardingResult(request)
    }
}