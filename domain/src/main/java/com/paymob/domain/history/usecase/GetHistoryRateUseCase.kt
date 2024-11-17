package com.paymob.domain.history.usecase

import com.paymob.domain.history.model.HistoryRate
import com.paymob.domain.history.repo.HistoryRateRepository
import javax.inject.Inject

class GetHistoryRateUseCase @Inject constructor(private val historyRateRepository: HistoryRateRepository) {

    suspend operator fun invoke(base: String, target: String, startDate:String, endDate: String): List<HistoryRate> {
        return historyRateRepository.getHistoryRate(base, target, startDate, endDate)
    }
}