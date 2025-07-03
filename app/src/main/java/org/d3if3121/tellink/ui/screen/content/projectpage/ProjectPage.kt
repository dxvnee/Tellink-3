package org.d3if3121.tellink.ui.screen.content.projectpage

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
import androidx.navigation.NavHostController
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.ui.component.ColumnCenter
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.GarisAbu
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.component.LoadingIndicatorBox
import org.d3if3121.tellink.ui.screen.content.component.ButtonMerahBehaviour
import org.d3if3121.tellink.ui.screen.content.component.MainLazyColumn
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.projectpage.component.ProjectTopContent
import org.d3if3121.tellink.ui.screen.content.projectpage.component.ProjectTopContentSecondary
import org.d3if3121.tellink.ui.viewmodel.MainViewModel


@Composable
fun ProjectPage(
    navControllerGlobal: NavHostController,
    lazyListState: LazyListState,
    currentUser: Mahasiswa,
    projectViewModel: ProjectPageViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
){
    LaunchedEffect(Unit){
        projectViewModel.getProjectListByNim(currentUser.nim)
    }

    ProjectPageComponent(
        lazyListState = lazyListState,
        currentUser = currentUser,
        projectViewModel = projectViewModel,
        mainViewModel = mainViewModel,
        navControllerGlobal = navControllerGlobal
    )
}

@Composable
fun ProjectPageComponent(
    lazyListState: LazyListState,
    currentUser: Mahasiswa,
    projectViewModel: ProjectPageViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    navControllerGlobal: NavHostController
){
    val secondPage by projectViewModel.secondPage.collectAsState()

    LaunchedEffect(secondPage){
        if (!secondPage) { projectViewModel.getProjectListByNim(currentUser.nim)
        } else { projectViewModel.getRequestListByNim(currentUser.nim) }
    }

    var dialogMessage by remember { mutableStateOf(false) }
    val alphaDone by mainViewModel.alphaDone.collectAsState()
    var judulDialog by remember { mutableStateOf("") }
    var isiDialog by remember { mutableStateOf("") }
    var search by remember { mutableStateOf("") }


    val projectList by projectViewModel.projectList.collectAsState()

    DialogMessage(
        visible = dialogMessage,
        textJudul = judulDialog,
        textDialog = isiDialog,
        textTombol = "OK"
    ){ dialogMessage = false }


    MainLazyColumn(
        list = projectList,
        showStickyPadding = true,
        alphaDone = alphaDone,
        lazyListState = lazyListState,

        topContent = { ProjectTopContent(projectViewModel) },
        topContentSecondary = {
            ProjectTopContentSecondary(
                search = search, onSearchChange = { search = it}, navControllerGlobal=  navControllerGlobal,
            )
        },
        mainContent = { project ->
            ProjectContent(project = project, secondPage = secondPage, projectViewModel = projectViewModel){ isiDialog = it }
        },
    )
}

@Composable
fun ProjectContent(
    project: Project?,
    secondPage: Boolean,
    projectViewModel: ProjectPageViewModel,
    onIsiDialogChange: (String) -> Unit
){
    val projectListResponse by projectViewModel.projectListByNim.collectAsState()
    val requestListResponse by projectViewModel.requestListByNim.collectAsState()

    StateHandler(
        viewModel = projectViewModel,
        listResponse = if (secondPage) projectListResponse else requestListResponse,

        isLoading = { ColumnCenter { LoadingIndicatorBox() } },
        isSuccess = { ProjectMainContent(project = project, projectViewModel = projectViewModel)},
        isFailure = { onIsiDialogChange("Error Fetching Data") },
    )
}


@Composable
fun ProjectMainContent(
    project: Project?,
    projectViewModel: ProjectPageViewModel
){
    if (project == null) return

    var mahasiswa = project.mahasiswa ?: Mahasiswa()

    KartuKonten(
        mahasiswa = mahasiswa,
        project = project,
        viewModel = projectViewModel,
        buttonbehaviour = ButtonMerahBehaviour.Project(
            buttonText = "Edit",
            onLikeClick = {},
            onCommentClick = {},
            onShareClick = {},
            onButtonClick = {},
            onTextClick = {},
        )
    )
    GarisAbu(Modifier.padding(start = 10.dp, end = 10.dp))
}




