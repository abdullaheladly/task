package com.paymob.domain.converter.repo

interface ConverterRepo {
    suspend fun getALlSymbols():List<String>
    suspend fun convertCurrency(from:String,to:String,amount:Float):Float
}