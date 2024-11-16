package com.paymob.data.converter.model.converter


import com.paymob.data.converter.utils.ErrorResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.paymob.data.converter.model.converter.Info
import com.paymob.data.converter.model.converter.Query

data class ConverterResponse(
    @SerializedName("date")
    @Expose
    val date: String?,
    @SerializedName("historical")
    @Expose
    val historical: String?,
    @SerializedName("info")
    @Expose
    val info: Info?,
    @SerializedName("query")
    @Expose
    val query: Query?,
    @SerializedName("result")
    @Expose
    val result: Double,
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("error")
    @Expose
    val error: ErrorResponse
)