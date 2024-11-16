package com.paymob.data.converter.mapper

import com.paymob.data.converter.model.converter.ConverterResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import javax.inject.Inject

class ConverterMapper @Inject constructor() {

    fun mapConverterResponse(converterResponse: ConverterResponse):Float {
        return converterResponse.result.toFloat()
    }

    fun mapSymbolsResponse(symbolsResponse: SymbolsResponse):List<String>{
        return symbolsResponse.symbols.keys.toList()
    }

    fun calculateConversionRate(
        rates: Map<String, Double>,
        fromCurrency: String,
        toCurrency: String,
        amount: Float
    ): Double {
        val fromRate = rates[fromCurrency]
        val toRate = rates[toCurrency]

        return if (fromRate != null && toRate != null) {
            (toRate / fromRate) * amount
        } else {
            throw IllegalArgumentException("Invalid currency code: $fromCurrency or $toCurrency")
        }
    }

}