package com.example.maskinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfo.model.Store
import com.example.maskinfo.repository.MaskService
import com.example.maskinfo.repository.MaskService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val service: MaskService

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        service = retrofit.create(MaskService::class.java)

        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true

        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(37.188078, 127.043002)
            itemLiveData.value = storeInfo.stores

            loadingLiveData.value = false
        }
    }
}