package org.d3if3121.tellink.ui.screen.auth.component.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.d3if3121.tellink.data.model.Response
import org.d3if3121.tellink.navigation.Screen
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.screen.auth.component.AuthContent
import org.d3if3121.tellink.ui.screen.auth.component.AuthHeader
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun <T> AuthPage(
    appMessage: String,
    headerTitle: String,
    dialogTitle: String,
    response: Response<T>,
    viewModel: AuthViewModel,
    navController: NavHostController,
    responseHandler: @Composable () -> Unit,
    content: @Composable () -> Unit,
    onMessageChange: (String) -> Unit
){
    var dialogMessage by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = Warna.PutihGelap){
        LaunchedEffect(appMessage){
            dialogMessage = appMessage.isNotEmpty()
        }

        responseHandler()

        AuthContent{
            AuthHeader(text = headerTitle)
            content()
        }

        DialogLoading(viewModel.loading)

        DialogMessage(
            visible =  dialogMessage,
            textJudul = dialogTitle,
            textDialog = appMessage,
            textTombol = "OK",
            onClick = {
                if(response is Response.Success){
                    navController.navigate(Screen.Login.route)
                } else {
                    onMessageChange("")
                    dialogMessage = false
                }
            }
        )
    }
}

