package com.example.maskinfo.repository

import com.example.maskinfo.model.StoreInfo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("sample.json")
    suspend fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ): StoreInfo
}