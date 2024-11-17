package com.paymob.data.history.mapper

import com.paymob.data.history.model.HistoryRateApiModel
import com.paymob.domain.history.model.HistoryRate
import javax.inject.Inject

class HistoryMapper @Inject constructor() {

     fun mapHistoryRate(historyRateApiModel: HistoryRateApiModel, target:String): List<HistoryRate> {
        val response = mutableListOf<HistoryRate>()
        if (historyRateApiModel.success) {
            historyRateApiModel.rates.map { rate ->
                for (k in rate.value) {
                    response.add(
                        HistoryRate(
                            base = historyRateApiModel.base,
                            date = rate.key,
                            target = k.key,
                            rate = k.value.toFloat()
                        )
                    )
                }


            }
        }
        return response
    }
}