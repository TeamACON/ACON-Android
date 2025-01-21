package com.acon.domain.repository

import com.acon.domain.model.upload.DotoriCount
import com.acon.domain.model.upload.KeyWord
import com.acon.domain.model.upload.Suggestions


interface UploadRepository {
    suspend fun getDotoriCount(): Result<DotoriCount>

    suspend fun getKeyWord(keyword: String): Result<KeyWord>

    suspend fun getSuggestions(latitude: Double, longitude: Double): Result<Suggestions>
}
