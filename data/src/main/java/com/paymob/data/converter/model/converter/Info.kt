package com.paymob.data.converter.model.converter


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Info(
    @SerializedName("rate")
    @Expose
    val rate: Double?,
    @SerializedName("timestamp")
    @Expose
    val timestamp: Int?
)