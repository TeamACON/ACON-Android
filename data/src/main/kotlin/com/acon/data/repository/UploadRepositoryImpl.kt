package com.acon.data.repository

import com.acon.data.datasource.remote.UploadRemoteDataSource
import com.acon.domain.model.upload.DotoriCount
import com.acon.domain.model.upload.KeyWord
import com.acon.domain.model.upload.Suggestions
import com.acon.domain.repository.UploadRepository
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val uploadRemoteDataSource: UploadRemoteDataSource
) : UploadRepository {
    override suspend fun getDotoriCount(): Result<DotoriCount> = runCatching {
        uploadRemoteDataSource.getDotoriCount().toDotoriCount()
    }

    override suspend fun getKeyWord(keyword: String): Result<KeyWord> = runCatching {
        uploadRemoteDataSource.getKeyWord(keyword).toKeyWord()
    }

    override suspend fun getSuggestions(
        latitude: Double,
        longitude: Double
    ): Result<Suggestions> = runCatching {
        uploadRemoteDataSource.getSuggestions(latitude, longitude).toSuggestions()
    }
}
