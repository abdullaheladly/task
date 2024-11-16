package com.paymob.data.converter.remote

import com.paymob.data.converter.model.converter.ConverterResponse
import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ConverterApi {

    companion object{
        private const val SYMBOLS="symbols"
        private const val CONVERTER="convert"
        private const val FROM="from"
        private const val TO="to"
        private const val AMOUNT="amount"
        private const val LATEST="latest"
        private const val ACCESS_KEY="access_key"

    }

    @GET(SYMBOLS)
    suspend fun getSymbols(
       @Query(ACCESS_KEY) key:String
    ): Response<SymbolsResponse>


    @GET(CONVERTER)
    suspend fun convertCurrency(
        @Query(ACCESS_KEY) key:String,
        @Query(FROM) from:String,
        @Query(TO) to:String,
        @Query(AMOUNT) amount:Float,
    ): Response<ConverterResponse>

    @GET(LATEST)
    suspend fun getCurrenciesData(
        @Query(ACCESS_KEY) key:String,
    ):Response<ChangeCurrencyResponse>

}