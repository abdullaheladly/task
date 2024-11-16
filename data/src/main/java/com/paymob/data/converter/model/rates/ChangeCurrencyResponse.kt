package com.paymob.data.converter.model.rates

import com.paymob.data.converter.utils.ErrorResponse
import com.google.gson.annotations.SerializedName

data class ChangeCurrencyResponse(
    @SerializedName("base")
    val base: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("rates")
    val rates: Map<String, Double>,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("error")
    val error: ErrorResponse?
)
