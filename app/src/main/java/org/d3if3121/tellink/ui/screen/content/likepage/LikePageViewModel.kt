package org.d3if3121.tellink.ui.screen.content.projectpage.projecteditpage

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
import org.d3if3121.tellink.data.model.dialog.DialogConfirmInterface
import org.d3if3121.tellink.data.model.dialog.DialogConfig
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaLikeResponse
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.ui.screen.content.component.ContentLoadingViewModel
import org.d3if3121.tellink.ui.screen.content.component.GambarHandler
import javax.inject.Inject

@HiltViewModel
class LikePageViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
):  ViewModel(), ContentLoadingViewModel<List<Mahasiswa>>, GambarHandler {

    override var loading: Boolean by mutableStateOf(false)

    override val gambarDialog = MutableStateFlow(false)
    override val gambarString =  MutableStateFlow("")

    private val _dialogMessage = MutableStateFlow(DialogConfig())
    val dialogMessage : StateFlow<DialogConfig> = _dialogMessage


    private val _mahasiswaLikeResponse = MutableStateFlow<MahasiswaLikeResponse>(Idle)
    val mahasiswaLikeResponse : StateFlow<MahasiswaLikeResponse> = _mahasiswaLikeResponse

    private val _mahasiswaLike = MutableStateFlow<List<Mahasiswa>?>(emptyList())
    val mahasiswaLike : StateFlow<List<Mahasiswa>?> = _mahasiswaLike


    fun getLike(projectId: String) = viewModelScope.launch {
        _mahasiswaLikeResponse.value = Loading
        delay(500)
        _mahasiswaLikeResponse.value = repo.getMahasiswaLikeByProjectId(projectId)
    }

    override fun dialogReset(){ dialogChange("", "") }

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

    override fun resetState() {
        _mahasiswaLikeResponse.value = Loading
        loading = false
    }

    override fun responseChange(data: List<Mahasiswa>?) {
        _mahasiswaLike.value = data
    }

}

