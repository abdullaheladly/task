package com.paymob.data.converter.datasource


import com.paymob.data.BuildConfig
import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import com.paymob.data.converter.remote.ConverterApi
import com.paymob.data.converter.utils.NetworkRouter
import javax.inject.Inject

class ConverterRemoteDataSource @Inject constructor(private val converterApi: ConverterApi, private val networkRouter: NetworkRouter) :
    ConverterDataSource {
    override suspend fun getALlSymbols(): SymbolsResponse {
        return networkRouter.invokeApi { converterApi.getSymbols(BuildConfig.SECRET_KEY) }
    }

    override suspend fun convertCurrency(): ChangeCurrencyResponse {
        return networkRouter.invokeApi { converterApi.getCurrenciesData(BuildConfig.SECRET_KEY) }
    }
}