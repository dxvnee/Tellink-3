package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.runtime.Composable
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Success

@Composable
fun <T> StateHandler(
    viewModel: ContentViewModel<T>,
    listResponse: Response<T>,

    isLoading: @Composable () -> Unit,
    isSuccess: @Composable () -> Unit,
    isFailure: (String) -> Unit,
){
    when(listResponse){
        is Failure -> { isFailure("Internal Server Error") }
        is Loading -> { isLoading() }
        is Success -> {
            viewModel.responseChange(listResponse.data)
            isSuccess()
        }
        is Idle -> {}
    }
}

@Composable
fun <T> StateHandlerAdd(
    viewModel: AddViewModel,
    response: Response<T>,
){
    when(response){
        is Loading -> { viewModel.loadingChange(true) }
        is Failure -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange("Post Failed", "Internal Server Error")
        }
        is Success -> {
            viewModel.loadingChange(false)
            viewModel.dialogChange("Post Success", "Post has uploaded!")
        }
        is Idle -> {}
    }
}