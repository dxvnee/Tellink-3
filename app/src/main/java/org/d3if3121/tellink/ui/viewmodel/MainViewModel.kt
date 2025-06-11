package org.d3if3121.tellink.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel() {

    val loading by mutableStateOf(false)

    private val _currentUser = MutableStateFlow(Mahasiswa())
    val currentUser: StateFlow<Mahasiswa> = _currentUser

    fun addCurrentUser(mahasiswa: Mahasiswa?) {
        mahasiswa?.let{
            _currentUser.value = it
        }
    }
}