package org.d3if3121.tellink.ui.screen.content.projectpage

import android.util.Log
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
import androidx.navigation.NavHostController
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.component.ColumnCenter
import org.d3if3121.tellink.ui.component.GarisAbu
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.component.LoadingIndicatorCenter
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
    val dialogMessage by projectViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }
    val alphaDone by mainViewModel.alphaDone.collectAsState()
    var search by remember { mutableStateOf("") }


    val projectList by projectViewModel.projectList.collectAsState()

    LaunchedEffect(secondPage){
        if (!secondPage) { projectViewModel.getProjectListByNim(currentUser.nim)
        } else { projectViewModel.getRequestListByNim(currentUser.nim) }
    }

    LaunchedEffect(dialogMessage){
        dialogActive = dialogMessage.message.isNotEmpty()
    }

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
            ProjectContent(project = project, secondPage = secondPage, navControllerGlobal = navControllerGlobal, projectViewModel = projectViewModel)
        },

    )


}

@Composable
fun ProjectContent(
    project: Project?,
    secondPage: Boolean,
    navControllerGlobal: NavHostController,
    projectViewModel: ProjectPageViewModel,
){
    val projectListResponse by projectViewModel.projectListByNim.collectAsState()
    val requestListResponse by projectViewModel.requestListByNim.collectAsState()

    StateHandler(
        viewModel = projectViewModel,
        listResponse = if (secondPage) requestListResponse else projectListResponse,

        isSuccess = {
            ProjectMainContent(
                project = project,
                projectViewModel = projectViewModel,
                navControllerGlobal = navControllerGlobal
            )
        },
        isLoading = { ColumnCenter(Modifier.height(500.dp)) { LoadingIndicatorCenter() } },
    )
}


@Composable
fun ProjectMainContent(
    project: Project?,
    projectViewModel: ProjectPageViewModel,
    navControllerGlobal: NavHostController
){
    if (project == null) return

    val mahasiswa = project.mahasiswa ?: Mahasiswa()

    KartuKonten(
        mahasiswa = mahasiswa,
        project = project,
        viewModel = projectViewModel,
        buttonbehaviour = ButtonMerahBehaviour.Project(
            buttonText = "Start!",
            onLikeClick = { projectViewModel.likeProject(project.id, mahasiswa.nim) },
            onLikeTextClick = { navControllerGlobal.navigate("${Screen.Like.route}/${project.id}") },
            onCommentClick = {},
            onShareClick = {},
            onButtonClick = {},
            onTextClick = {},
        )
    ){ navControllerGlobal.navigate("${Screen.ProjectEdit.route}/${project.id}") }

    GarisAbu(Modifier.padding(start = 10.dp, end = 10.dp))
}




