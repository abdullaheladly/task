package com.paymob.presentation.base

sealed class LoadingViewState {
    object ShowLoadingViewState : LoadingViewState()
    object HideLoadingViewState : LoadingViewState()
}
