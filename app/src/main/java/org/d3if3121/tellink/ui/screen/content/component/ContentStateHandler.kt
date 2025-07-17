package org.d3if3121.tellink.ui.screen.content.component

import android.util.Log
import androidx.compose.runtime.Composable
import org.d3if3121.tellink.data.model.dialog.DialogInterface
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Success

@Composable
fun <T> StateHandler(
    viewModel: ContentLoadingViewModel<T>,
    listResponse: Response<T>,

    isLoading: @Composable () -> Unit = {},
    isSuccess: @Composable () -> Unit,
){
    when(listResponse){
        is Loading -> {
            viewModel.loadingChange(true)
            isLoading()
        }
        is Failure -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange("Loading Failed", "Internal Server Error")
        }
        is Success -> {
            viewModel.loadingChange(false)
            viewModel.responseChange(listResponse.data)
            isSuccess()
        }
        is Idle -> {}
    }
}

@Composable
fun <T> StateHandlerPost(
    viewModel: LoadingDialogHandler,
    response: Response<T>,

    dialogInterface: DialogInterface = DialogInterface.Add
){
    when(response){
        is Loading -> { viewModel.loadingChange(true) }
        is Failure -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange(dialogInterface.failureTitle, dialogInterface.failureMessage)
        }
        is Success -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange(dialogInterface.successTitle, dialogInterface.successMessage)
        }
        is Idle -> {}
    }
}

