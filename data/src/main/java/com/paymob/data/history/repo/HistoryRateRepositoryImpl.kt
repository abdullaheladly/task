package com.paymob.data.history.repo



import com.paymob.data.history.datasource.RemoteHistoryRateDataSource
import com.paymob.domain.history.model.HistoryRate
import com.paymob.domain.history.repo.HistoryRateRepository
import javax.inject.Inject

class HistoryRateRepositoryImpl @Inject constructor(
    private val remoteHistoryRateDataSource: RemoteHistoryRateDataSource,
) : HistoryRateRepository {

    override suspend fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):
            List<HistoryRate> = remoteHistoryRateDataSource.getHistoryRate(base=base, target=target, startDate=startDate, endDate=endDate)

}

