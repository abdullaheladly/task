package com.paymob.data.converter.model.converter


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Query(
    @SerializedName("amount")
    @Expose
    val amount: Int?,
    @SerializedName("from")
    @Expose
    val from: String?,
    @SerializedName("to")
    @Expose
    val to: String?
)