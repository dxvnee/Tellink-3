package org.d3if3121.tellink.ui.screen.projectpage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.d3if3121.tellink.data.model.MahasiswaLogin
import org.d3if3121.tellink.data.repository.interfaces.MahasiswaListInterface
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectPageViewModel @Inject constructor(
    private val repo: MahasiswaListInterface
): ViewModel() {

}