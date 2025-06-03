package org.d3if3121.tellink.ui.screen.homepage

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