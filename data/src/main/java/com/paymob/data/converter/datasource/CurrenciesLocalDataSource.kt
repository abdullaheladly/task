package com.paymob.data.converter.datasource

import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrenciesLocalDataSource  @Inject constructor() : CurrenciesDataSource {

    private  var currenciesDataModel: ChangeCurrencyResponse? = null
    override suspend fun saveCurrencies(value: ChangeCurrencyResponse) {
        currenciesDataModel=value
    }

    override suspend fun getCurrencies(): ChangeCurrencyResponse? {
        return currenciesDataModel
    }
}
