package org.d3if3121.tellink.ui.screen.content.commentpage

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
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.data.model.comment.CommentRequest
import org.d3if3121.tellink.data.model.dialog.DialogConfirmInterface
import org.d3if3121.tellink.data.model.dialog.DialogConfig
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.repository.interfaces.AddCommentResponse
import org.d3if3121.tellink.data.repository.interfaces.CommentProjectResponse
import org.d3if3121.tellink.data.repository.interfaces.CommentReplyResponse
import org.d3if3121.tellink.data.repository.interfaces.DeleteCommentResponse
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.ui.screen.content.component.ContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.DoubleContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler
import javax.inject.Inject

@HiltViewModel
class CommentPageViewModel @Inject constructor(
    private val repo: ProjectListInterface
):  ViewModel(), DoubleContentLoadingViewModel<List<Comment>>, GambarHandler {

    override var loading: Boolean by mutableStateOf(false)

    override val gambarDialog = MutableStateFlow(false)
    override val gambarString =  MutableStateFlow("")

    data class ReplyName(val name: String, val commentId: String)

    private val _dialogMessage = MutableStateFlow(DialogConfig())
    val dialogMessage : StateFlow<DialogConfig> = _dialogMessage

    private val _replyName = MutableStateFlow(ReplyName("", ""))
    val replyName : StateFlow<ReplyName> = _replyName

    private val _commentListResponse = MutableStateFlow<CommentProjectResponse>(Idle)
    val commentListResponse : StateFlow<CommentProjectResponse> = _commentListResponse

    private val _replyListResponse = MutableStateFlow<CommentReplyResponse>(Idle)
    val replyListResponse : StateFlow<CommentReplyResponse> = _replyListResponse


    private val _addCommentResponse = MutableStateFlow<AddCommentResponse>(Idle)
    val addCommentResponse : StateFlow<AddCommentResponse> = _addCommentResponse

    private val _deleteCommentResponse = MutableStateFlow<DeleteCommentResponse>(Idle)
    val deleteCommentResponse : StateFlow<DeleteCommentResponse> = _deleteCommentResponse


    private val _commentList = MutableStateFlow<List<Comment>?>(emptyList())
    val commentList : StateFlow<List<Comment>?> = _commentList

    private val _replyList = MutableStateFlow<List<Comment>?>(emptyList())
    val replyList : StateFlow<List<Comment>?> = _replyList


    fun getComment(projectId: String) = viewModelScope.launch {
        _commentListResponse.value = Loading
        delay(500)
        _commentListResponse.value = repo.getCommentByProjectId(projectId)
    }

    fun getReply(commentId: String) = viewModelScope.launch {
        _replyListResponse.value = Loading
        delay(500)
        _replyListResponse.value = repo.getCommentReplyById(commentId)
    }

    fun addComment(nim: String, projectId: String, comment: String, commentId: String) = viewModelScope.launch {
        _addCommentResponse.value = Loading

         val commentRequest = CommentRequest(
            nim = nim,
            projectId = projectId,
            comment = comment,
            commentId = if (commentId != "") commentId else null
        )

        delay(500)
        _addCommentResponse.value = repo.addComment(commentRequest)
    }

    fun deleteComment(commentId: String) = viewModelScope.launch {
        _deleteCommentResponse.value = Loading
        delay(500)

        _deleteCommentResponse.value = repo.deleteComment(commentId)
    }


    override fun dialogReset(){ dialogChange("", "") }

    override fun gambarChange(active: Boolean) {
        gambarDialog.value = active
    }

    fun replyNameChange(name: String, commentId: String) {
        _replyName.value = ReplyName(name, commentId)
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

    override fun responseSecondChange(data: List<Comment>?) {
        _replyList.value = data
    }

    override fun resetState() {
        _commentListResponse.value = Loading
        loading = false
    }

    fun resetSecondState() {
        _replyListResponse.value = Loading
        loading = false
    }

    override fun responseChange(data: List<Comment>?) {
        _commentList.value = data
    }

}

