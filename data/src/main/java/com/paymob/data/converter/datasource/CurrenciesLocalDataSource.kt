package com.paymob.data.converter.datasource

import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrenciesLocalDataSource  @Inject constructor() : CurrenciesDataSource {
    // we should rely on Room here but just for time limitation i did this approach
    private  var currenciesDataModel: ChangeCurrencyResponse? = null
    override suspend fun saveCurrencies(value: ChangeCurrencyResponse) {
        currenciesDataModel=value
    }

    override suspend fun getCurrencies(): ChangeCurrencyResponse? {
        return currenciesDataModel
    }
}
