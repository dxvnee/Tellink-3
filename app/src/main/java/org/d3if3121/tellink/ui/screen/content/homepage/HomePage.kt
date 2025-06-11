package org.d3if3121.tellink.ui.screen.content.homepage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.data.model.response.Response.Idle
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.repository.interfaces.ProjectListResponse
import org.d3if3121.tellink.ui.animation.paddingDpAnimation
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahBehaviour
import org.d3if3121.tellink.ui.screen.content.component.MainLazyColumn
import org.d3if3121.tellink.ui.screen.content.homepage.component.HomeTopContent


val TOP_BAR_HEIGHT = 70.dp

@Composable
fun HomePage(
    lazyListState: LazyListState,
    homeViewModel: HomeViewModel = hiltViewModel(),
){
    val projectListResponse by homeViewModel.projectListResponse.collectAsState()

    var dialogMessage by remember { mutableStateOf(false) }
    var judulDialog by remember { mutableStateOf("") }
    var isiDialog by remember { mutableStateOf("") }

    DialogMessage(
        visible = dialogMessage,
        textJudul = judulDialog,
        textDialog = isiDialog,
        textTombol = "OK"
    ){ dialogMessage = false }

    ProjectListStateHandler(
        homeViewModel = homeViewModel,
        projectListResponse = projectListResponse,

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
    val padding = paddingDpAnimation(lazyListState)
    val projectList by homeViewModel.projectList.collectAsState()

    MainLazyColumn(
        topContent = { HomeTopContent() },
        mainContent = { project -> HomeMainContent(mahasiswa, project, homeViewModel) },
        padding = padding,
        lazyListState = lazyListState,
        list = projectList
    )
}

@Composable
fun HomeMainContent(
    mahasiswa: Mahasiswa,
    project: Project,
    homeViewModel: HomeViewModel
){
    var requestornot by remember { mutableStateOf(false) }

    KartuKonten(
        mahasiswa = mahasiswa,
        project = project,
        buttonbehaviour = ButtonMerahBehaviour.Dynamic(
            active = requestornot ,
            onclick = { homeViewModel.addRequest(project.id, mahasiswa.nim) },
            onclickcancel = { homeViewModel.deleteRequest(project.id, mahasiswa.nim) },
            onrequestchange = { requestornot = it},
        ),
    )
    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
fun ProjectListStateHandler(
    homeViewModel: HomeViewModel,
    projectListResponse: ProjectListResponse,

    isLoading: @Composable () -> Unit,
    isSuccess: @Composable () -> Unit,
    isFailure: (String) -> Unit,
){
    when(projectListResponse){
        is Failure -> { isFailure("Internal Server Error") }
        is Loading -> { isLoading() }
        is Success -> {
            homeViewModel.projectListChange(projectListResponse.data)
            isSuccess()
        }
        is Idle -> {}
    }
}




