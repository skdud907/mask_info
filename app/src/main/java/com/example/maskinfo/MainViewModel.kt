package com.example.maskinfo

import android.location.Location
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

    var location: Location? = null

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
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true

        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(location?.latitude!!, location?.longitude!!)
            for (store in storeInfo.stores) {
                val distance = LocationDistance.getDistance(location?.latitude!!, location?.longitude!!, store.lat, store.lng)
                store.distance = distance
            }

            itemLiveData.value = storeInfo.stores.sorted()

            loadingLiveData.value = false
        }
    }
}