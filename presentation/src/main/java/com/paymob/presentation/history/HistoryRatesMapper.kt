package com.paymob.presentation.history

import com.paymob.domain.history.model.HistoryRate
import javax.inject.Inject

class HistoryRatesMapper @Inject constructor() {
     fun mapHistoryRate(data: List<HistoryRate>): HistoryRateListModel {

        return HistoryRateListModel(
            items = data.map {
                HistoryRateListItemModel(
                    base = it.base,
                    target = it.target,
                    date = it.date,
                    rate = it.rate
                )
            },
        )
    }
}