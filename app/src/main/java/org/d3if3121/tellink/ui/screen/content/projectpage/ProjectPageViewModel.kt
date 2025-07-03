package org.d3if3121.tellink.ui.screen.content.projectpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.data.repository.interfaces.ProjectWithMahasiswaResponse
import org.d3if3121.tellink.ui.screen.content.component.ContentViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectPageViewModel @Inject constructor(
    private val repo: ProjectListInterface
): ViewModel(), ContentViewModel<List<Project>> {
    override var loading: Boolean by mutableStateOf(false)

    private val _secondPage = MutableStateFlow(true)
    val secondPage: StateFlow<Boolean> = _secondPage

    private val _gambarDialog = MutableStateFlow(false)
    val gambarDialog: StateFlow<Boolean> = _gambarDialog

    private val _gambarString = MutableStateFlow("")
    val gambarString: StateFlow<String> = _gambarString

    private val _projectList = MutableStateFlow<List<Project>?>(emptyList())
    val projectList: StateFlow<List<Project>?> = _projectList

    private val _projectListByNim = MutableStateFlow<ProjectWithMahasiswaResponse>(Idle)
    val projectListByNim: StateFlow<ProjectWithMahasiswaResponse> = _projectListByNim

    private val _requestListByNim = MutableStateFlow<ProjectWithMahasiswaResponse>(Idle)
    val requestListByNim: StateFlow<ProjectWithMahasiswaResponse> = _requestListByNim

    fun getProjectListByNim(nim: String) = viewModelScope.launch {
        _projectListByNim.value = Response.Loading
        responseChange(emptyList())

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

    override fun onDialogGambar(active: Boolean, gambarBaru: String) {
        gambarChange(active)
        gambarStringChange(gambarBaru)
    }

    fun gambarChange(active: Boolean){
        _gambarDialog.value = active
    }

    fun gambarStringChange(gambarBaru: String){
        _gambarString.value = gambarBaru
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
}