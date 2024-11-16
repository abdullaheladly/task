package com.paymob.domain.converter.usecase

import com.paymob.domain.converter.repo.ConverterRepo
import javax.inject.Inject

class GetAllSymbolsUseCase @Inject constructor(private val converterRepo: ConverterRepo) {

    suspend operator fun invoke(): List<String> {
        return converterRepo.getALlSymbols()
    }
}
