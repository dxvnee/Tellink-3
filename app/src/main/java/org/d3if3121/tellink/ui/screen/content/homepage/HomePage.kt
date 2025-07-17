package org.d3if3121.tellink.ui.screen.content.homepage

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.ui.component.ColumnCenter
import org.d3if3121.tellink.ui.component.GarisAbu
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.component.LoadingIndicatorCenter
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

    LaunchedEffect(Unit){ homeViewModel.getProjectListWithMahasiswa() }

    var dialogMessage by remember { mutableStateOf(false) }
    var judulDialog by remember { mutableStateOf("") }
    var isiDialog by remember { mutableStateOf("") }

    val projectList by homeViewModel.projectList.collectAsState()


    MainLazyColumn(
        list = projectList,
        lazyListState = lazyListState,
        topContent = { HomeTopContent() },
        mainContent = { project -> HomeContent(homeViewModel, project)},
    )
}

@Composable
fun HomeContent(
    homeViewModel: HomeViewModel,
    project: Project?,
){
    val projectListResponse by homeViewModel.projectListWithMahasiswaResponse.collectAsState()

    StateHandler(
        viewModel = homeViewModel,
        listResponse = projectListResponse,
        isSuccess = { HomeMainContent(project, homeViewModel) },
        isLoading = { ColumnCenter(Modifier.height(500.dp)) { LoadingIndicatorCenter() } }
    )
}

@Composable
fun HomeMainContent(
    project: Project?,
    homeViewModel: HomeViewModel
){
    var requestornot by remember { mutableStateOf(false) }

    if (project != null) {
        val mahasiswa = project.mahasiswa ?: Mahasiswa()

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







