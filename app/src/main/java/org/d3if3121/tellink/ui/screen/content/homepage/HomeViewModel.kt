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
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.AddRequestResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.data.repository.interfaces.ProjectListResponse
import org.d3if3121.tellink.ui.screen.auth.component.template.AuthViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProjectListInterface
): ViewModel(), AuthViewModel {

    override var loading: Boolean by mutableStateOf(false)

    private val _projectListResponse = MutableStateFlow<ProjectListResponse>(Idle)
    val projectListResponse: StateFlow<ProjectListResponse> = _projectListResponse

    private val _requestResponse = MutableStateFlow<AddRequestResponse>(Idle)
    val requestResponse: StateFlow<AddRequestResponse> = _requestResponse

    private val _projectList = MutableStateFlow<List<Project>?>(emptyList())
    val projectList: StateFlow<List<Project>?> = _projectList

    init {
        getProjectList()
    }

    private fun getProjectList() = viewModelScope.launch {
        delay(1000)
        repo.getProjectList().collect(){
            _projectListResponse.value = it
        }
    }

    fun deleteRequest(projectId: String, nim: String) = viewModelScope.launch {
        _requestResponse.value = repo.deleteRequest(projectId, nim)
    }

    fun addRequest(projectId: String, nim: String) = viewModelScope.launch {
        _requestResponse.value = repo.addRequest(projectId, nim)
    }

    fun projectListChange(projectList: List<Project>?){
        _projectList.value = projectList
    }

    fun requestResponseChange(response: AddRequestResponse){
        _requestResponse.value = response
    }

    override fun resetState(){
        loadingChange(false)
        requestResponseChange(Idle)
    }
    override fun loadingChange(state: Boolean){
        loading = state
    }
}

//
//
//@Composable
//fun HomeStateHandler(projectviewmodel: HomeViewModel, projectListResponse: ProjectListResponse){
//
//
//    when(val addRequestResponse = projectviewmodel.addRequestResponse){
//        is Loading -> {
//
//        }
//        is Success -> {
//            projectviewmodel.resetAddRequestResponse()
//        }
//        is Failure -> printError(addRequestResponse.e)
//        Response.Idle -> {}
//    }
//    when(val deleteRequestResponse = projectviewmodel.deleteRequestResponse){
//        is Loading -> {
//
//        }
//        is Success -> {
//            projectviewmodel.resetDeleteRequestResponse()
//        }
//        is Failure -> printError(deleteRequestResponse.e)
//        Response.Idle -> {}
//    }
//}
