package com.paymob.data.converter.utils


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ErrorResponse(
    @SerializedName("code")
    @Expose
    val code: Int,
    @SerializedName("info")
    @Expose
    val info: String?
)