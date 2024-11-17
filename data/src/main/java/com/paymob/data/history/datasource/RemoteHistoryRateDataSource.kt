package com.paymob.data.history.datasource

import com.paymob.domain.history.model.HistoryRate


interface RemoteHistoryRateDataSource {

   suspend fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):List<HistoryRate>

}