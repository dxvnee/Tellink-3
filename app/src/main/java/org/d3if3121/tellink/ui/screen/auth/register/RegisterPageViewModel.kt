package org.d3if3121.tellink.ui.screen.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.RegisterResponse
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterPageViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel(), AuthViewModel {

    override var loading: Boolean by mutableStateOf(false)

    private val _registerResponse = MutableStateFlow<RegisterResponse>(Idle)
    val registerResponse: StateFlow<RegisterResponse> = _registerResponse

    private fun addMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        delay(1000)
        registerResponseChange(repo.registerMahasiswa(mahasiswa))
    }

    fun handleRegister(
        nama: String, nim: String, password: String,
        confirmPassword: String, appMessage: (String) -> Unit,
    ){
        if (!(nim.isNotEmpty() && nama.isNotEmpty() && password.isNotEmpty())) {
            appMessage("All fields shouldn't be empty.")
            return
        }
        if (!isValidPassword(password)){
            appMessage("Password should has 8 characters contains letters, numbers, and not symbols")
            return
        }
        if (password != confirmPassword){
            appMessage("Password doesn't match!")
            return
        }

        val mahasiswa =  Mahasiswa(
            nim = nim, password = password,
            nama = nama, jurusan = "Unknown",
            angkatan = "Unknown",
        )

        addMahasiswa(mahasiswa)
        loadingChange(true)
    }

    private fun registerResponseChange(response: RegisterResponse){
        _registerResponse.value = response
    }
    override fun loadingChange(state: Boolean){
        loading = state
    }
    override fun resetState() {
        loadingChange(false)
        registerResponseChange(Idle)
    }

    private fun isValidPassword(password: String): Boolean{
        val minLength = 8
        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        val hasSymbols = password.any { !it.isLetterOrDigit() }

        return password.length >= minLength && hasLetter && hasDigit && hasSymbols
    }

}


@Composable
fun RegisterStateHandler(
    registerPageViewModel: RegisterPageViewModel,
    registerResponse: RegisterResponse,
    appMessage: (String) -> Unit
){
    when(registerResponse) {
        is Success -> {
            appMessage("Your Account has Registered!")
        }
        is Failure -> {
            appMessage(registerResponse.e?.message ?: "Unknown error")
            registerPageViewModel.resetState()
        }
        is Loading -> { registerPageViewModel.loading = true }
        is Idle -> { registerPageViewModel.loading = false }
    }
}