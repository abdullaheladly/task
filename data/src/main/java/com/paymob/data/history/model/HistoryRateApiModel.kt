package com.paymob.data.history.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paymob.data.converter.utils.ErrorResponse


data class HistoryRateApiModel(
    @SerializedName("base")
    @Expose val base: String,
    @SerializedName("end_date")
    @Expose
    val endDate: String,
    @SerializedName("start_date")
    @Expose
    val startDate: String,
    @SerializedName("timeseries")
    @Expose
    val timeseries: Boolean,
    @SerializedName("rates")
    @Expose
    val rates: Map<String, Map<String, Double>>,
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("error")
    @Expose
    val error: ErrorResponse
)