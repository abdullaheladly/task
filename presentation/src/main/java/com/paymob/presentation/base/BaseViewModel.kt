package com.paymob.presentation.base


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paymob.domain.exception.MessageException
import com.paymob.domain.exception.NoInternetException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException

open class BaseViewModel() : ViewModel() {

    protected var mLoadingState =
        MutableSharedFlow<LoadingViewState>()
    val loadingState = mLoadingState.asSharedFlow()

    private val mFailureViewStateError = MutableSharedFlow<FailureViewState>()
    val failureViewStateError = mFailureViewStateError.asSharedFlow()

    protected val baseExceptionHandlerHandler = CoroutineExceptionHandler { _, e ->
        viewModelScope.launch(Dispatchers.Main) {
            onFailure(e)
        }
    }

    private suspend fun onShowLoading() {
        mLoadingState.emit(LoadingViewState.ShowLoadingViewState)
    }

    private suspend fun onHideLoading() {
        mLoadingState.emit(LoadingViewState.HideLoadingViewState)
    }

    suspend fun <T : Any> enqueueApiCall(apiCall: suspend () -> T): T {
        onShowLoading()
        val result = apiCall.invoke()
        onHideLoading()
        return result
    }

    private suspend fun onFailure(throwable: Throwable) {
        when (throwable) {
            is NoInternetException -> {
                mFailureViewStateError.emit(FailureViewState.NoInternetViewState)
            }
            is IllegalArgumentException -> {
                mFailureViewStateError.emit(FailureViewState.IllegalArgumentsViewState(throwable.message.toString()))
            }
            is FileNotFoundException -> {
                mFailureViewStateError.emit(FailureViewState.FileNotFoundViewState(throwable.message.toString()))
            }
            is MessageException -> {
                Log.d("abdullah","in message")

                mFailureViewStateError.emit(FailureViewState.UnExpectedViewState((throwable as MessageException).message.toString()))
            }
            else -> {
                Log.d("abdullah","in else")
                mFailureViewStateError.emit(FailureViewState.UnExpectedViewState(throwable.message.toString()))

            }
        }.apply { mLoadingState.emit(LoadingViewState.HideLoadingViewState) }
    }

    fun resetFailureViewState() {
        viewModelScope.launch {
            mFailureViewStateError.emit(FailureViewState.NoFailure)
        }
    }
}
