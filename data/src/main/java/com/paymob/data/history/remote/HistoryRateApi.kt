package com.paymob.data.history.remote

import com.paymob.data.history.model.HistoryRateApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface HistoryRateApi {

    @GET(value = "/fixer/timeseries")
    suspend fun fetchHistory(
        @Query("start_date") start_date:String,
        @Query("end_date") end_date:String,
        @Query("base") base:String,
        @Query("symbols") symbols:String): Response<HistoryRateApiModel>
}