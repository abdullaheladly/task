package com.paymob.data.converter.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ApiError(
    @SerializedName("error")
    @Expose
    val error: ErrorResponse
)
