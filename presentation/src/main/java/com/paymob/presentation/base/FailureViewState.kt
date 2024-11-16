package com.paymob.presentation.base

sealed class FailureViewState {
    object NoInternetViewState : FailureViewState()
    data class UnExpectedViewState(val message: String) : FailureViewState()
    data class IllegalArgumentsViewState(val message: String) : FailureViewState()
    data class FileNotFoundViewState(val message: String) : FailureViewState()
    object NoFailure : FailureViewState()
}
