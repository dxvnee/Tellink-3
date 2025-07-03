package org.d3if3121.tellink.ui.screen.auth.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.R
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.component.ColumnPadding
import org.d3if3121.tellink.ui.component.InputPasswordNative
import org.d3if3121.tellink.ui.component.InputPutihNative
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.screen.auth.login.LoginPageViewModel
import org.d3if3121.tellink.ui.screen.auth.register.RegisterPageViewModel

@Composable
fun AuthForm(content: @Composable () -> Unit){
    ColumnPadding {
        LazyColumn {
            item {
                content()
            }
        }
    }
}

@Composable
fun LoginForm(
    loginViewModel: LoginPageViewModel = hiltViewModel(),
    navController: NavHostController,
    appMessageOnChange: (String) -> Unit,
){
    var nim by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }

    AuthField(text = stringResource(id = R.string.nim)){
        InputPutihNative(
            input = nim,
            placeholder = stringResource(id = R.string.enter_nim),
            onInputChange = { nim = it },
            keyboardType = KeyboardType.Number,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Space(18)

    AuthField(text = stringResource(id = R.string.password)){
        InputPasswordNative(
            input = password,
            placeholder = stringResource(id = R.string.enter_password),
            onInputChange = { password = it },
            keyboardType = KeyboardType.Password,
            passwordVisible = passwordVisible,
            modifier = Modifier.fillMaxWidth(),
            iconWeight = 6f
        )
    }

    Space(26)

    TombolTeksBawah(
        textTombol = "LOGIN",
        textTeks1 = "Don't have account? ",
        textTeks2 = "Register!",
        onClickText = { navController.navigate(Screen.Register.route) },
        onClickButton = {
            loginViewModel.handleLogin(
                nim = nim,
                password = password,
                appMessage = appMessageOnChange
            )
        },
    )
}

@Composable
fun RegisterForm(
    registerViewModel: RegisterPageViewModel = hiltViewModel(),
    navController: NavHostController,
    appMessageOnChange: (String) -> Unit,
){
    var nim by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }
    var confirmPasswordVisible = remember { mutableStateOf(false) }

    AuthField(text = stringResource(id = R.string.nim)){
        InputPutihNative(
            input = nim,
            placeholder = stringResource(id = R.string.enter_nim),
            onInputChange = { nim = it },
            keyboardType = KeyboardType.Number,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Space(18)

    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        AuthField(text = stringResource(id = R.string.password), Modifier.weight(1f).padding(end = 10.dp)){
            InputPasswordNative(
                input = password,
                placeholder = stringResource(id = R.string.enter_password),
                onInputChange = { password = it },
                keyboardType = KeyboardType.Password,
                passwordVisible = passwordVisible,
            )
        }
        AuthField(text = stringResource(id = R.string.confirm_password), Modifier.weight(1f).padding(start = 10.dp)){
            InputPasswordNative(
                input = confirmPassword,
                placeholder = stringResource(id = R.string.enter_password),
                onInputChange = { confirmPassword = it },
                keyboardType = KeyboardType.Password,
                passwordVisible = confirmPasswordVisible,
            )
        }
    }

    Space(18)

    AuthField(text = stringResource(id = R.string.full_name)){
        InputPutihNative(
            input = nama,
            placeholder = stringResource(id = R.string.enter_name),
            onInputChange = { nama = it },
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth()
        )
    }
    Space(26)

    TombolTeksBawah(
        textTombol = "REGISTER",
        textTeks1 = "Already have account? ",
        textTeks2 = "Login",
        onClickText = { navController.navigate(Screen.Login.route) },
        onClickButton = {
            registerViewModel.handleRegister(
                nama = nama,
                nim = nim,
                password = password,
                confirmPassword = confirmPassword,
                appMessage = appMessageOnChange
            )
        },
    )
}