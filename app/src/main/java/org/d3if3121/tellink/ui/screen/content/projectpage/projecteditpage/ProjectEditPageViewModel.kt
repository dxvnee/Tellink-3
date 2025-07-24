package org.d3if3121.tellink.ui.screen.content.projectpage.projecteditpage

import android.content.Context
import android.net.Uri
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.dialog.DialogConfirmInterface
import org.d3if3121.tellink.data.model.dialog.DialogConfig
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.project.ProjectEdit
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.repository.interfaces.AddProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.DeleteProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.EditProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectIdResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.ui.formula.toMultipartBody
import org.d3if3121.tellink.ui.formula.toRequestBody
import org.d3if3121.tellink.ui.formula.uriToFile
import org.d3if3121.tellink.ui.screen.content.component.ContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProjectEditPageViewModel @Inject constructor(
    private val repo: ProjectListInterface
):  ViewModel(), ContentLoadingViewModel<Project>, GambarHandler {

    override var loading: Boolean by mutableStateOf(false)

    override val gambarDialog = MutableStateFlow(false)
    override val gambarString =  MutableStateFlow("")

    private val _dialogMessage = MutableStateFlow(DialogConfig())
    val dialogMessage : StateFlow<DialogConfig> = _dialogMessage

    private val _projectEditResponse = MutableStateFlow<EditProjectResponse>(Idle)
    val projectEditResponse : StateFlow<EditProjectResponse> = _projectEditResponse

    private val _projectDeleteResponse = MutableStateFlow<DeleteProjectResponse>(Idle)
    val projectDeleteResponse : StateFlow<DeleteProjectResponse> = _projectDeleteResponse

    private val _projectIdResponse = MutableStateFlow<ProjectIdResponse>(Idle)
    val projectIdResponse : StateFlow<ProjectIdResponse> = _projectIdResponse

    private val _projectId = MutableStateFlow<Project?>(Project())
    val projectId : StateFlow<Project?> = _projectId



    fun getProject(projectId: String) = viewModelScope.launch {
        _projectIdResponse.value = Loading
        delay(500)
        _projectIdResponse.value = repo.getProjectById(projectId)
    }

    fun editDialog(onClick: () -> Unit){
        dialogChangeInterface(DialogConfirmInterface.EDIT,
            onClick = { dialogReset(); onClick() },
            onFailure = { dialogReset() }
        )
    }

    override fun dialogReset(){ dialogChange("", "") }

    fun handleEdit(id: String, judul: String, desc: String, selectedTag: List<String>, imageData: String, currentUser: Mahasiswa, imageUri: Uri?, context: Context) {
        projectEditResponseChange(Loading)

        val image: File? = if(imageUri == null){ null } else { uriToFile(context, imageUri) }
        var imageMultipart: MultipartBody.Part? = null

        if(imageUri != null){ imageMultipart = image?.let { toMultipartBody(it, context, imageUri) } }

        if (!inputValidation(judul, desc, selectedTag)) return

        val project = ProjectEdit(
            nim = currentUser.nim,
            title = judul,
            desc = desc,
            tag = selectedTag,
            image = imageData
        )

        val projectId = toRequestBody(id)
        val projectPart = toRequestBody(project)

        editProject(projectId, projectPart, imageMultipart)
    }

    fun editProject(id: RequestBody, projectPart: RequestBody, imageMultipart: MultipartBody.Part?) = viewModelScope.launch {
        projectEditResponseChange(Loading)
        delay(500)

        projectEditResponseChange(repo.editProject(id, projectPart, imageMultipart))
    }

    fun deleteDialog(projectId: String) {
        dialogChangeInterface(DialogConfirmInterface.DELETE,
            onClick = { deleteProject(projectId) },
            onFailure = { dialogReset() }
        )
    }

    private fun deleteProject(projectId: String) = viewModelScope.launch {
        _projectDeleteResponse.value = Loading
        delay(500)
        _projectDeleteResponse.value = repo.deleteProject(projectId)
    }

    override fun gambarChange(active: Boolean) {
        gambarDialog.value = active
    }

    override fun gambarStringChange(gambarBaru: String) {
        gambarString.value = gambarBaru
    }

    override fun onDialogGambar(active: Boolean, gambarBaru: String) {
        gambarChange(false)
        gambarStringChange(gambarBaru)
    }

    override fun loadingChange(state: Boolean){
        loading = state
    }


    fun dialogChangeInterface(dialog: DialogConfirmInterface, onClick: () -> Unit, onFailure: () -> Unit) {
        dialogChange(
            title = dialog.title,
            message = dialog.message,
            onClick = onClick,
            buttonText = dialog.buttontext,
            onFailure = onFailure,
            dismissText = dialog.dismissText
        )
    }

    override fun dialogChange(title: String, message: String, buttonText: String, onClick: () -> Unit, dismissText: String, onFailure: () -> Unit ){
        _dialogMessage.value = DialogConfig(title, message, buttonText, onClick, dismissText, onFailure)
    }

    fun dialogChangeResponse(title: String, message: String){
        projectEditResponseChange(Idle)
        dialogChange(title, message, "OK", onClick = { dialogReset() })
    }

    fun projectEditResponseChange(response: AddProjectResponse){
        _projectEditResponse.value = response
    }

    override fun resetState() {
        _projectEditResponse.value = Loading
        loading = false
    }

    override fun responseChange(data: Project?) {
        _projectId.value = data
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

