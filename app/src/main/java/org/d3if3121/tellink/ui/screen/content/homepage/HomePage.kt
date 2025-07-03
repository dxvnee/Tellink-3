package org.d3if3121.tellink.ui.screen.content.homepage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.GarisAbu
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahBehaviour
import org.d3if3121.tellink.ui.screen.content.component.MainLazyColumn
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.homepage.component.HomeTopContent

val TOP_BAR_HEIGHT = 10.dp

@Composable
fun HomePage(
    lazyListState: LazyListState,
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val projectListResponse by homeViewModel.projectListWithMahasiswaResponse.collectAsState()

    var dialogMessage by remember { mutableStateOf(false) }
    var judulDialog by remember { mutableStateOf("") }
    var isiDialog by remember { mutableStateOf("") }

    DialogMessage(
        visible = dialogMessage,
        textJudul = judulDialog,
        textDialog = isiDialog,
        textTombol = "OK"
    ){ dialogMessage = false }

    StateHandler(
        viewModel = homeViewModel,
        listResponse = projectListResponse,

        isLoading = { DialogLoading(true) },
        isSuccess = { HomeContent(homeViewModel, lazyListState) },
        isFailure = { isiDialog = it },
    )
}

@Composable
fun HomeContent(
    homeViewModel: HomeViewModel,
    lazyListState: LazyListState
) {
    val projectList by homeViewModel.projectList.collectAsState()

    MainLazyColumn(
        list = projectList,
        lazyListState = lazyListState,
        topContent = { HomeTopContent() },
        mainContent = { project -> HomeMainContent(project, homeViewModel) },
    )
}

@Composable
fun HomeMainContent(
    project: Project?,
    homeViewModel: HomeViewModel
){
    var requestornot by remember { mutableStateOf(false) }

    if (project != null) {
        var mahasiswa = project.mahasiswa ?: Mahasiswa()

        KartuKonten(
            mahasiswa = mahasiswa,
            project = project,
            viewModel = homeViewModel,
            buttonbehaviour = ButtonMerahBehaviour.Dynamic(
                active = requestornot,
                onclick = { homeViewModel.addRequest(project.id, mahasiswa.nim) },
                onclickcancel = { homeViewModel.deleteRequest(project.id, mahasiswa.nim) },
                onrequestchange = { requestornot = it },

                buttonText = "+ Request",
                onLikeClick = {},
                onCommentClick = {},
                onShareClick = {},
                onButtonClick = {},
            ),
        )
    }
    GarisAbu(Modifier.padding(start = 10.dp, end = 10.dp))
}







