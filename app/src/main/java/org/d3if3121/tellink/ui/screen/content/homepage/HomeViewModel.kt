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
import org.d3if3121.tellink.data.model.dialog.DialogMessage
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.AddRequestResponse
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

    private val _dialogMessage = MutableStateFlow(DialogMessage())
    val dialogMessage : StateFlow<DialogMessage> = _dialogMessage

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


    fun getProjectListWithMahasiswa() = viewModelScope.launch {
        projectListWithMahasiswaChange(Response.Loading)
        delay(500)

        projectListWithMahasiswaChange(repo.getProjectWithMahasiswa())
    }

    private fun projectListWithMahasiswaChange(state: ProjectWithMahasiswaResponse) {
        _projectListWithMahasiswaResponse.value = state
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

    override fun dialogChange(title: String, message: String) {
        _dialogMessage.value = DialogMessage(title, message)
    }
}
