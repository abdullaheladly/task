package com.paymob.data.converter.model.symbols


import com.paymob.data.converter.utils.ErrorResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SymbolsResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean?,
    @SerializedName("symbols")
    @Expose
    val symbols: Map<String, String>,
    @SerializedName("error")
    @Expose
    val error: ErrorResponse
)