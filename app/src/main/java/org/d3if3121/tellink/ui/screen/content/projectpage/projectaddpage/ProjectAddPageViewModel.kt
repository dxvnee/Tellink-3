package org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.dialog.DialogMessage
import org.d3if3121.tellink.data.model.project.ProjectAdd
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.repository.interfaces.AddProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.ui.formula.toMultipartBody
import org.d3if3121.tellink.ui.formula.toRequestBody
import org.d3if3121.tellink.ui.formula.uriToFile
import org.d3if3121.tellink.ui.screen.content.component.LoadingDialogHandler
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProjectAddPageViewModel @Inject constructor(
    private val repo: ProjectListInterface
):  ViewModel(), LoadingDialogHandler {

    override var loading: Boolean by mutableStateOf(false)

    private val _gambarDialog = MutableStateFlow(false)
    val gambarDialog: StateFlow<Boolean> = _gambarDialog

    private val _dialogMessage = MutableStateFlow(DialogMessage())
    val dialogMessage : StateFlow<DialogMessage> = _dialogMessage

    private val _projectAddResponse = MutableStateFlow<AddProjectResponse>(Idle)
    val projectAddResponse : StateFlow<AddProjectResponse> = _projectAddResponse


    fun handleAdd(judul: String, desc: String, selectedTag: List<String>, currentUser: Mahasiswa, imageUri: Uri?, context: Context) {
        projectAddResponseChange(Loading)

        val image: File? = if(imageUri == null){ null } else { uriToFile(context, imageUri) }
        var imageMultipart: MultipartBody.Part? = null

        if(imageUri != null){ imageMultipart = image?.let { toMultipartBody(it, context, imageUri) } }

        if (!inputValidation(judul, desc, selectedTag)) return

        val project = ProjectAdd(
            nim = currentUser.nim,
            title = judul,
            desc = desc,
            tag = selectedTag
        )

        val projectPart = toRequestBody(project)
        addProject(projectPart, imageMultipart)
    }

    fun addProject(projectPart: RequestBody, imageMultipart: MultipartBody.Part?) = viewModelScope.launch {
        projectAddResponseChange(repo.addProject(projectPart, imageMultipart))
    }

    override fun loadingChange(state: Boolean){
        loading = state
    }

    override fun dialogChange(title: String, message: String){
        _dialogMessage.value = DialogMessage(title, message)
    }

    fun dialogChangeResponse(title: String, message: String){
        projectAddResponseChange(Idle)
        dialogChange(title, message)
    }

    fun projectAddResponseChange(response: AddProjectResponse){
        _projectAddResponse.value = response
    }
    fun resetState() {
        _projectAddResponse.value = Loading
        loading = false
    }

    fun gambarChange(active: Boolean){
        _gambarDialog.value = active
    }


    fun responseChange(data: String?) {
        _projectAddResponse.value = Loading
    }

    private fun isValidTitle(title: String): Boolean{
        val minLength = 10
        return title.length >= minLength
    }

    fun inputValidation(judul: String, desc: String, selectedTag: List<String>): Boolean{
        if(judul.isEmpty() || desc.isEmpty()) {
            dialogChangeResponse("Post Failed", "Title and description shouldn't be empty!")
            return false
        }
        if(!isValidTitle(judul)) {
            dialogChangeResponse("Post Failed", "Title is too short!")
            return false
        }
        if(selectedTag.size < 3) {
            dialogChangeResponse("Post Failed", "Tag should be selected 3!")
            return false
        }
        return true
    }
}

