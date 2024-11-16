package com.paymob.data.converter.datasource

import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse


interface ConverterDataSource {
    suspend fun getALlSymbols(): SymbolsResponse
    suspend fun convertCurrency(): ChangeCurrencyResponse
}