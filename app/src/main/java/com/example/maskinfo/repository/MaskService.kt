package com.example.maskinfo.repository

import com.example.maskinfo.model.StoreInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("sample.json")
    suspend fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ): StoreInfo

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/"
    }

}