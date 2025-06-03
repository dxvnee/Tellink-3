package org.d3if3121.tellink.ui.screen.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.ui.screen.auth.component.AuthForm
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthPage
import org.d3if3121.tellink.ui.screen.auth.component.LoginForm

@Composable
fun LoginPage(navController: NavHostController){

    var loginViewModel: LoginPageViewModel = hiltViewModel()
    var appMessage by remember { mutableStateOf("") }
    val loginResponse by loginViewModel.loginResponse.collectAsState()

    AuthPage(
        headerTitle = "Unite Minds, Create Impact!",
        dialogTitle = "Login Failed",
        response = loginResponse,
        appMessage = appMessage,
        onMessageChange = {appMessage = ""},
        responseHandler = {
            LoginStateHandler(
                loginResponse = loginResponse,
                navController = navController,
                appMessage = { appMessage = it }
            )
        },
        viewModel = loginViewModel,
        navController = navController,
        content = {
            AuthForm {
                LoginForm(
                    loginViewModel = loginViewModel,
                    navController = navController,
                    appMessageOnChange = { appMessage = it }
                )
            }
        }
    )
}


