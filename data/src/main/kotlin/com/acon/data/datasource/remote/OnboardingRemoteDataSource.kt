package com.acon.data.datasource.remote

import com.acon.data.api.remote.OnboardingApi
import com.acon.data.dto.request.PostOnboardingResultRequest
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val onboardingApi: OnboardingApi
) {

    suspend fun postResult(request: PostOnboardingResultRequest) {
        onboardingApi.postOnboardingResult(request)
    }
}