package com.paymob.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.paymob.presentation.dialog.ErrorDialog
import com.paymob.presentation.dialog.LoadingDialog

@Composable
fun BaseComposableScreen(
    viewModel: BaseViewModel,
    failureViewState: FailureViewState = FailureViewState.NoFailure,
    content: @Composable () -> Unit
) {
    val failureViewState = viewModel.failureViewStateError.collectAsState(initial = failureViewState)
    val loadingState = viewModel.loadingState.collectAsState(initial = LoadingViewState.HideLoadingViewState)
    val showDialog = remember { mutableStateOf(false) }

    LoadingDialog(loadingState.value == LoadingViewState.ShowLoadingViewState)

    when (failureViewState.value) {
        is FailureViewState.FileNotFoundViewState -> {
            showDialog.value = true
            ErrorDialog(htmlMessage = (failureViewState.value as FailureViewState.FileNotFoundViewState).message, shouldDismiss = showDialog, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            })
        }
        is FailureViewState.IllegalArgumentsViewState -> {
            showDialog.value = true
            ErrorDialog(htmlMessage = (failureViewState.value as FailureViewState.IllegalArgumentsViewState).message, shouldDismiss = showDialog, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            })
        }
        FailureViewState.NoInternetViewState -> {
            showDialog.value = true
        }
        FailureViewState.NoFailure -> {
        }

        is FailureViewState.UnExpectedViewState ->{
            showDialog.value = true
            ErrorDialog(htmlMessage = (failureViewState.value as FailureViewState.UnExpectedViewState).message , shouldDismiss = showDialog, onDismiss = {
                showDialog.value = false
                viewModel.resetFailureViewState()
            })
        }
    }

    content()
}
