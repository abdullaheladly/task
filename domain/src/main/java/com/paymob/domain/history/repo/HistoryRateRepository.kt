package com.paymob.domain.history.repo

import com.paymob.domain.history.model.HistoryRate


interface HistoryRateRepository {
     suspend fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):List<HistoryRate>
}