package org.d3if3121.tellink.ui.screen.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.ui.screen.auth.component.AuthForm
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthPage
import org.d3if3121.tellink.ui.screen.auth.component.RegisterForm

@Composable
fun RegisterPage(navController: NavHostController){
    var registerViewModel: RegisterPageViewModel = hiltViewModel()
    val registerResponse by registerViewModel.registerResponse.collectAsState()
    var appMessage by remember { mutableStateOf("") }

    AuthPage(
        headerTitle = "Join the Collaboration Movement!",
        dialogTitle = if(registerResponse is Response.Success){ "Register Success!" } else { "Register Failed" },
        response = registerResponse,
        appMessage = appMessage,
        onMessageChange = {appMessage = ""},
        responseHandler = {
            RegisterStateHandler(
                registerPageViewModel = registerViewModel,
                registerResponse = registerResponse,
                appMessage = { appMessage = it }
            )
        },
        viewModel = registerViewModel,
        navController = navController,
        content = {
            AuthForm {
                RegisterForm(
                    registerViewModel = registerViewModel,
                    navController = navController,
                    appMessageOnChange = { appMessage = it }
                )
            }
        }
    )
}


