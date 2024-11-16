package com.paymob.data.converter.datasource

import com.paymob.data.converter.model.rates.ChangeCurrencyResponse


interface CurrenciesDataSource {

    suspend fun saveCurrencies(value: ChangeCurrencyResponse)
    suspend fun getCurrencies(): ChangeCurrencyResponse?
}