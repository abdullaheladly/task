package com.paymob.data.converter.repo


import com.paymob.data.converter.datasource.ConverterDataSource
import com.paymob.data.converter.datasource.CurrenciesDataSource
import com.paymob.data.converter.mapper.ConverterMapper
import com.paymob.domain.converter.repo.ConverterRepo
import javax.inject.Inject

class ConverterRepoImpl @Inject constructor(private val converterDataSource: ConverterDataSource, private val currenciesDataSource: CurrenciesDataSource, private val converterMapper: ConverterMapper) :
    ConverterRepo {
    override suspend fun getALlSymbols(): List<String> {
        return converterMapper.mapSymbolsResponse(converterDataSource.getALlSymbols())
    }

    override suspend fun convertCurrency(from: String, to: String, amount: Float): Float {
        val rates = currenciesDataSource.getCurrencies()?.rates
            ?: converterDataSource.convertCurrency().also { currenciesDataSource.saveCurrencies(it) }.rates

        return converterMapper.calculateConversionRate(rates, from, to,amount).toFloat()
    }

}