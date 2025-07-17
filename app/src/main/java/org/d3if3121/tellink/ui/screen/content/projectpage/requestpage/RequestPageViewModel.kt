package org.d3if3121.tellink.ui.screen.content.projectpage.requestpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaReqByProjectIdResponse
import javax.inject.Inject

@HiltViewModel
class RequestPageViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel() {
    var loading: Boolean by mutableStateOf(false)

    private val _mahasiswaListResponse = MutableStateFlow<MahasiswaReqByProjectIdResponse>(Idle)
    val mahasiswaListResponse : StateFlow<MahasiswaReqByProjectIdResponse> = _mahasiswaListResponse

    fun getMahasiswaListByRequest(projectId: String) = viewModelScope.launch {
        mahasiswaListResponseChange(repo.getMahasiswaReqByProjectId(projectId))
    }

    fun mahasiswaListResponseChange(response: MahasiswaReqByProjectIdResponse){
        _mahasiswaListResponse.value = response
    }
}