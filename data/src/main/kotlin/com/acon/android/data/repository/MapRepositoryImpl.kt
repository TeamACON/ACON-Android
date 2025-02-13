package com.acon.android.data.repository

import com.acon.android.data.datasource.remote.MapRemoteDataSource
import com.acon.android.data.error.runCatchingWith
import com.acon.android.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapRemoteDataSource: MapRemoteDataSource
) : MapRepository {

    override suspend fun fetchLegalAddressName(
        latitude: Double,
        longitude: Double
    ): Result<String> {
        return runCatchingWith {
            mapRemoteDataSource.fetchReverseGeocoding(latitude, longitude).results.filter {
                it.name == "legalcode"
            }.firstOrNull()?.region?.area3?.name ?: ""
        }
    }
}