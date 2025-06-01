package org.d3if3121.tellink.ui.screen.homepage

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
import org.d3if3121.tellink.data.model.MahasiswaLogin
import org.d3if3121.tellink.data.model.Response.Idle
import org.d3if3121.tellink.data.model.Response.Loading
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthViewModel
import javax.inject.Inject
//
//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val repo: MahasiswaListInterface
//): ViewModel(), AuthViewModel {
//
//    override var loading: Boolean by mutableStateOf(false)
//
//    private val _loginResponse = MutableStateFlow<LoginResponse>(Idle)
//    val loginResponse: StateFlow<LoginResponse> = _loginResponse
//
//    private fun loginMahasiswa(response: MahasiswaLogin) = viewModelScope.launch {
//        delay(1000)
//        loginResponseChange(repo.loginMahasiswa(response.nim, response.password))
//    }
//
//    fun handleLogin(nim: String, password: String, appMessage: (String) -> Unit){
//        loginResponseChange(Loading)
//
//        if(nim.isNotEmpty() && password.isNotEmpty()){
//            var mahasiswa = MahasiswaLogin(
//                nim = nim,
//                password = password
//            )
//            loginMahasiswa(mahasiswa)
//        } else {
//            loginResponseChange(Idle)
//            appMessage("All fields shouldn't be empty.")
//        }
//    }
//    private fun loginResponseChange(response: LoginResponse){
//        _loginResponse.value = response
//    }
//
//    override fun resetState(){
//        loadingChange(false)
//        loginResponseChange(Idle)
//    }
//    override fun loadingChange(state: Boolean){
//        loading = state
//    }
//}