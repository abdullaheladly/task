package com.paymob.data.converter.remote

import com.paymob.data.converter.model.converter.ConverterResponse
import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ConverterApi {

    companion object{
        private const val SYMBOLS="/fixer/symbols"
        private const val LATEST="/fixer/latest"
    }

    @GET(SYMBOLS)
    suspend fun getSymbols(
    ): Response<SymbolsResponse>


    @GET(LATEST)
    suspend fun getCurrenciesData(
    ):Response<ChangeCurrencyResponse>

}