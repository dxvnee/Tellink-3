package org.d3if3121.tellink.ui.screen.content.projectpage

import android.util.Log
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
import org.d3if3121.tellink.data.repository.interfaces.LikeProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.data.repository.interfaces.ProjectWithMahasiswaResponse
import org.d3if3121.tellink.ui.screen.content.component.ContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler
import javax.inject.Inject

@HiltViewModel
class ProjectPageViewModel @Inject constructor(
    private val repo: ProjectListInterface
): ViewModel(), ContentLoadingViewModel<List<Project>>, GambarHandler {
    override var loading: Boolean by mutableStateOf(false)

    private val _dialogMessage = MutableStateFlow(DialogConfig())
    val dialogMessage : StateFlow<DialogConfig> = _dialogMessage

    private val _secondPage = MutableStateFlow(false)
    val secondPage: StateFlow<Boolean> = _secondPage

    override val gambarDialog = MutableStateFlow(false)

    override val gambarString = MutableStateFlow("")

    private val _projectList = MutableStateFlow<List<Project>?>(emptyList())
    val projectList: StateFlow<List<Project>?> = _projectList

    private val _projectListByNim = MutableStateFlow<ProjectWithMahasiswaResponse>(Idle)
    val projectListByNim: StateFlow<ProjectWithMahasiswaResponse> = _projectListByNim

    private val _requestListByNim = MutableStateFlow<ProjectWithMahasiswaResponse>(Idle)
    val requestListByNim: StateFlow<ProjectWithMahasiswaResponse> = _requestListByNim

    private val _likeResponse = MutableStateFlow<LikeProjectResponse>(Idle)
    val likeResponse: StateFlow<LikeProjectResponse> = _likeResponse


    override fun dialogReset(){ dialogChange("", "") }

    fun getProjectListByNim(nim: String) = viewModelScope.launch {
        _projectListByNim.value = Response.Loading
        responseChange(emptyList())
        delay(500)

        _projectListByNim.value = repo.getProjectByNim(nim)
    }

    fun getRequestListByNim(nim: String) = viewModelScope.launch {
        _requestListByNim.value = Response.Loading
        responseChange(emptyList())

        _requestListByNim.value = repo.getRequestByNim(nim)
    }

    override fun responseChange(data: List<Project>?){
        _projectList.value = data?.toList()
    }

    fun likeProject(projectId: String, nim: String) = viewModelScope.launch {
        _likeResponse.value = repo.likeProject(projectId, nim)
    }

    override fun onDialogGambar(active: Boolean, gambarBaru: String) {
        gambarChange(active)
        gambarStringChange(gambarBaru)
    }

    override fun gambarChange(active: Boolean){
        gambarDialog.value = active
    }

    override fun gambarStringChange(gambarBaru: String){
        gambarString.value = gambarBaru
    }

    fun secondPageChange(active: Boolean){
        _secondPage.value = active
    }

    override fun resetState(){
        loadingChange(false)
    }

    override fun loadingChange(state: Boolean){
        loading = state
    }

    override fun dialogChange(title: String, message: String, buttonText: String, onClick: () -> Unit, dismissText: String, onFailure: () -> Unit ){
        _dialogMessage.value = DialogConfig(title, message, buttonText, onClick, dismissText, onFailure)
    }
}