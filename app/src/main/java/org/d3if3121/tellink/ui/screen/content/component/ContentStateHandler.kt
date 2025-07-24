package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.d3if3121.tellink.data.model.dialog.DialogStateInterface
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
            viewModel.dialogChange("Loading Failed", listResponse.e.toString(), "OK"){
                viewModel.dialogReset()
            }
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
    navController: NavController,
    dialogInterface: DialogStateInterface
){
    when(response){
        is Loading -> { viewModel.loadingChange(true) }
        is Failure -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange(dialogInterface.failureTitle, dialogInterface.failureMessage, buttonText =  "OK",  onClick = {
                viewModel.dialogReset()
            })
        }
        is Success -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange(dialogInterface.successTitle, dialogInterface.successMessage, buttonText =  "OK", onClick = {
                navController.popBackStack()
            })
        }
        is Idle -> {}
    }
}


@Composable
fun <T> StateHandlerSecond(
    viewModel: DoubleContentLoadingViewModel<T>,
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
            viewModel.dialogChange("Loading Failed", listResponse.e.toString(), "OK"){
                viewModel.dialogReset()
            }
        }
        is Success -> {
            viewModel.loadingChange(false)
            viewModel.responseSecondChange(listResponse.data)
            isSuccess()
        }
        is Idle -> {}
    }
}



