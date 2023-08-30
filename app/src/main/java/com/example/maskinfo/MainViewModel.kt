package com.example.maskinfo

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maskinfo.model.Store
import com.example.maskinfo.repository.MaskService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: MaskService
): ViewModel() {
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    var location: Location? = null

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