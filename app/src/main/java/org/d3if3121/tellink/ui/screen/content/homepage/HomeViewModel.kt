package org.d3if3121.tellink.ui.screen.content.homepage

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
import org.d3if3121.tellink.data.model.dialog.DialogConfig
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.AddRequestResponse
import org.d3if3121.tellink.data.repository.interfaces.LikeProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.data.repository.interfaces.ProjectListResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectWithMahasiswaResponse
import org.d3if3121.tellink.ui.screen.content.component.ContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProjectListInterface): ViewModel(),
    ContentLoadingViewModel<List<Project>>,
    GambarHandler
{

    override var loading: Boolean by mutableStateOf(false)

    private var _projectId = MutableStateFlow("")
    val projectId : StateFlow<String> = _projectId

    private var _commentDialog = MutableStateFlow(false)
    val commentDialog : StateFlow<Boolean> = _commentDialog



    private val _dialogMessage = MutableStateFlow(DialogConfig())
    val dialogMessage : StateFlow<DialogConfig> = _dialogMessage

    override val gambarDialog = MutableStateFlow(false)
    override val gambarString = MutableStateFlow("")

    private val _projectListResponse = MutableStateFlow<ProjectListResponse>(Idle)
    val projectListResponse: StateFlow<ProjectListResponse> = _projectListResponse

    private val _requestResponse = MutableStateFlow<AddRequestResponse>(Idle)
    val requestResponse: StateFlow<AddRequestResponse> = _requestResponse

    private val _projectList = MutableStateFlow<List<Project>?>(emptyList())
    val projectList: StateFlow<List<Project>?> = _projectList

    private val _projectListWithMahasiswaResponse = MutableStateFlow<ProjectWithMahasiswaResponse>(Idle)
    val projectListWithMahasiswaResponse: StateFlow<ProjectWithMahasiswaResponse> = _projectListWithMahasiswaResponse

    private val _likeResponse = MutableStateFlow<LikeProjectResponse>(Idle)
    val likeResponse: StateFlow<LikeProjectResponse> = _likeResponse

    override fun dialogReset(){ dialogChange("", "") }

    fun getProjectListWithMahasiswa(nim: String) = viewModelScope.launch {
        projectListWithMahasiswaChange(Response.Loading)
        delay(500)

        projectListWithMahasiswaChange(repo.getProjectWithMahasiswa(nim))
    }

    private fun projectListWithMahasiswaChange(state: ProjectWithMahasiswaResponse) {
        _projectListWithMahasiswaResponse.value = state
    }

    fun likeProject(projectId: String, nim: String) = viewModelScope.launch {
        _likeResponse.value = repo.likeProject(projectId, nim)
    }

    fun deleteRequest(projectId: String, nim: String) = viewModelScope.launch {
        _requestResponse.value = repo.deleteRequest(projectId, nim)
    }

    fun addRequest(projectId: String, nim: String) = viewModelScope.launch {
        _requestResponse.value = repo.addRequest(projectId, nim)
    }

    override fun responseChange(data: List<Project>?) {
        _projectList.value = data
    }

    fun requestResponseChange(response: AddRequestResponse){
        _requestResponse.value = response
    }

    override fun onDialogGambar(active: Boolean, gambarBaru: String){
        gambarChange(active)
        gambarStringChange(gambarBaru)
    }

    override fun gambarChange(active: Boolean){
        gambarDialog.value = active
    }

    override fun gambarStringChange(gambarBaru: String){
        gambarString.value = gambarBaru
    }

    override fun resetState(){
        loadingChange(false)
        requestResponseChange(Idle)
    }

    override fun loadingChange(state: Boolean){
        loading = state
    }

    fun commentDialogChange(state: Boolean, projectId: String){
        _commentDialog.value = state
        _projectId.value = projectId
    }

    override fun dialogChange(title: String, message: String, buttonText: String, onClick: () -> Unit, dismissText: String, onFailure: () -> Unit ){
        _dialogMessage.value = DialogConfig(title, message, buttonText, onClick, dismissText, onFailure)
    }
}
