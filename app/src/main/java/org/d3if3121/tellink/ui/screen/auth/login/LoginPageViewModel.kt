package org.d3if3121.tellink.ui.screen.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3121.tellink.data.model.MahasiswaLogin
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.LoginResponse
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.navigation.Screen
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthViewModel
import org.d3if3121.tellink.ui.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel(), AuthViewModel {

    override var loading: Boolean by mutableStateOf(false)

    private val _loginResponse = MutableStateFlow<LoginResponse>(Idle)
    val loginResponse: StateFlow<LoginResponse> = _loginResponse

    private fun loginMahasiswa(response: MahasiswaLogin) = viewModelScope.launch {
        delay(1000)
        loginResponseChange(repo.loginMahasiswa(response))
    }

    fun handleLogin(nim: String, password: String, appMessage: (String) -> Unit){
        loginResponseChange(Loading)

        if(nim.isNotEmpty() && password.isNotEmpty()){
            var mahasiswa = MahasiswaLogin(
                nim = nim,
                password = password
            )
            loginMahasiswa(mahasiswa)
        } else {
            loginResponseChange(Idle)
            appMessage("All fields shouldn't be empty.")
        }
    }

    private fun loginResponseChange(response: LoginResponse){
        _loginResponse.value = response
    }

    override fun resetState(){
        loadingChange(false)
        loginResponseChange(Idle)
    }
    override fun loadingChange(state: Boolean){
        loading = state
    }
}

@Composable
fun LoginStateHandler(
    loginResponse: LoginResponse, mainViewModel: MainViewModel = hiltViewModel(),
    loginPageViewModel: LoginPageViewModel = hiltViewModel(), navController: NavHostController,
    appMessage: (String) -> Unit
){
    when(loginResponse){
        is Success -> {
            loginPageViewModel.resetState()

            mainViewModel.addCurrentUser(loginResponse.data)
            navController.navigate(Screen.Home.route)
        }
        is Failure -> {
            loginPageViewModel.resetState()

            appMessage(loginResponse.e!!.message.toString())
        }
        is Loading -> { loginPageViewModel.loadingChange(true) }
        is Idle -> { loginPageViewModel.loadingChange(false)}
    }
}