package com.paymob.data.history.datasource


import com.paymob.data.BuildConfig
import com.paymob.data.converter.utils.NetworkRouter
import com.paymob.data.history.mapper.HistoryMapper
import com.paymob.data.history.remote.HistoryRateApi
import com.paymob.domain.history.model.HistoryRate
import javax.inject.Inject

class RemoteHistoryRateDataSourceImpl @Inject constructor(private val historyRateApi: HistoryRateApi, private val networkRouter: NetworkRouter, private val historyMapper: HistoryMapper) :
    RemoteHistoryRateDataSource {

    override suspend fun getHistoryRate(base: String, target: String, startDate:String, endDate: String): List<HistoryRate> {
        return historyMapper.mapHistoryRate(networkRouter.invokeApi { historyRateApi.fetchHistory(base = base, start_date = startDate, end_date = endDate, symbols = target) },target)

    }
}