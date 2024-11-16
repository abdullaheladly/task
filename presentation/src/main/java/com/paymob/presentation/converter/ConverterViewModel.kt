package com.paymob.presentation.converter

import com.paymob.domain.converter.usecase.ConvertCurrencyUseCase
import com.paymob.domain.converter.usecase.GetAllSymbolsUseCase
import com.paymob.presentation.base.BaseViewModel
import com.paymob.presentation.base.IoDispatcher
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val getAllSymbolsUseCase: GetAllSymbolsUseCase,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private var mCurrenciesDataStateFlow = MutableStateFlow(listOf<String>())
    val currenciesDataStateFlow = mCurrenciesDataStateFlow.asStateFlow()

    private var mResultStateFlow = MutableStateFlow(0f)
    val resultStateFlow = mResultStateFlow.asStateFlow()

    private var mFromCurrencyStateFlow = MutableStateFlow("")
    val fromCurrencyStateFlow = mFromCurrencyStateFlow.asStateFlow()

    private var mToCurrencyStateFlow = MutableStateFlow("")
    val toCurrencyStateFlow = mToCurrencyStateFlow.asStateFlow()

    private var currentAmount=0f

    init {
        getAllSymbols()
    }


     private fun getAllSymbols() {
        viewModelScope.launch(baseExceptionHandlerHandler+defaultDispatcher) {
            enqueueApiCall {
               mCurrenciesDataStateFlow.value= getAllSymbolsUseCase.invoke()
                mFromCurrencyStateFlow.value=mCurrenciesDataStateFlow.value.first()
                mToCurrencyStateFlow.value=mCurrenciesDataStateFlow.value.last()
            }
        }
    }
     fun convert(amount:Float) {
         currentAmount=amount
        viewModelScope.launch(baseExceptionHandlerHandler+defaultDispatcher) {
            mResultStateFlow.value= convertCurrencyUseCase.invoke(mFromCurrencyStateFlow.value,mToCurrencyStateFlow.value,amount)
        }
    }

    fun changeFromCurrency(value:String){
        mFromCurrencyStateFlow.value=value
        mResultStateFlow.value=0f
    }
    fun changeToCurrency(value:String){
        mToCurrencyStateFlow.value=value
        mResultStateFlow.value=0f
    }
    fun swap(){
        mFromCurrencyStateFlow.value = mToCurrencyStateFlow.value.also { mToCurrencyStateFlow.value = mFromCurrencyStateFlow.value }
        convert(currentAmount)
    }


}