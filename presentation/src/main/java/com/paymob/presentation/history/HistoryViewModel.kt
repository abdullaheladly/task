package com.paymob.presentation.history

import androidx.lifecycle.viewModelScope
import com.paymob.domain.converter.usecase.GetAllSymbolsUseCase
import com.paymob.domain.history.usecase.GetHistoryRateUseCase
import com.paymob.presentation.base.BaseViewModel
import com.paymob.presentation.base.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: GetHistoryRateUseCase,
    private val historyRatesMapper: HistoryRatesMapper,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private val mHistoryRatesFlow = MutableStateFlow(
        HistoryRateListModel(
            emptyList()
        )
    )
    val historyRatesFlow = mHistoryRatesFlow.asStateFlow()

    fun getDate(minusDays: Long = 0): String {
        val zoneId = ZoneId.systemDefault()
        return LocalDate.now(zoneId).minusDays(minusDays).toString()
    }

    fun filterOtherPopularRates(
        historyRateListModel: List<HistoryRateListItemModel>,
        target: String
    ) =
        historyRateListModel.filter { history -> history.target != target && history.date == getDate() }

    fun filterRates(historyRateListModel: List<HistoryRateListItemModel>, target: String) =
        historyRateListModel.filter { history -> history.target == target }

    fun getTargetCurrencies(target: String): String {
        //get all currencies from local instead
        val popularCurrencies = mutableListOf(
            "EUR",
            "EGP",
            "GBP",
            "AUD",
            "CAD",
            "CHF",
            "HKD",
            "SGD",
            "USD",
            "CNY",
            "SEK"
        )
        val topMostPopularCurrencies = popularCurrencies.filter { s -> s != target }.toMutableList()
        topMostPopularCurrencies.add(target)
        return topMostPopularCurrencies.joinToString()
    }

    fun getHistoryRates(base: String, target: String, endDate: String, startDate: String) {
        viewModelScope.launch(baseExceptionHandlerHandler + defaultDispatcher) {
            mHistoryRatesFlow.value=historyRatesMapper.mapHistoryRate(useCase.invoke(base = base, target = target, startDate = startDate, endDate = endDate))

        }
    }
}