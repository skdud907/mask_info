package com.example.maskinfo.model

import com.google.gson.annotations.SerializedName

data class Store (
    @SerializedName("addr")
    var addr: String,
    var code: String,
    var created_at: String?,
    var lat: Double,
    var lng: Double,
    var name: String,
    var remain_stat: String?,
    var stock_at: String?,
    var type: String,
) : Comparable<Store>
{
    var distance: Double? = null

    override fun compareTo(other: Store): Int {
        if (this.distance == null && other.distance == null) {
            return 0
        } else if (this.distance == null) {
            return -1
        } else if (other.distance == null) {
            return 1
        }
        return this.distance!!.compareTo(other.distance!!)
    }
}