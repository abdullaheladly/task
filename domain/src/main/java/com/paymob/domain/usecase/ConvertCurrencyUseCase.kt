package com.paymob.domain.usecase

import com.paymob.domain.repo.ConverterRepo
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(private val converterRepo: ConverterRepo) {

    suspend operator fun invoke(from:String,to:String,amount:Float): Float {
        return converterRepo.convertCurrency(from, to, amount)
    }
}
