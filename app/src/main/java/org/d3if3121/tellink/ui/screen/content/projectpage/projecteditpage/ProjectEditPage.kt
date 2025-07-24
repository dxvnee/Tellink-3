package org.d3if3121.tellink.ui.screen.content.projectpage.projecteditpage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.data.model.dialog.DialogStateInterface
import org.d3if3121.tellink.ui.component.ColumnPaddingLazy
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.ScaffoldSurfacePutih
import org.d3if3121.tellink.ui.component.TopBarContent
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.component.StateHandlerPost
import org.d3if3121.tellink.ui.screen.content.projectpage.component.ProjectForm
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@Composable
fun ProjectEditPage(
    navController: NavController,
    mainViewModel: MainViewModel,
    projectId: String
){
    val projectEditViewModel: ProjectEditPageViewModel = hiltViewModel()
    val projectEditResponse by projectEditViewModel.projectEditResponse.collectAsState()
    val projectDeleteResponse by projectEditViewModel.projectDeleteResponse.collectAsState()


    val dialogMessage by projectEditViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }


    LaunchedEffect(dialogMessage){ dialogActive = dialogMessage.message.isNotEmpty() }

    StateHandlerPost(
        viewModel = projectEditViewModel,
        response = projectEditResponse,
        navController = navController,
        dialogInterface = DialogStateInterface.EDIT
    )

    StateHandlerPost(
        viewModel = projectEditViewModel,
        response = projectDeleteResponse,
        navController = navController,
        dialogInterface = DialogStateInterface.DELETE
    )

    LaunchedEffect(Unit){ projectEditViewModel.getProject(projectId) }

    ScaffoldSurfacePutih(
        topbar = {
            TopBarContent(
                text = "Edit Post",
                icon = Icons.Filled.ArrowBackIosNew,
                navController = navController
            )
        },
        content = { ColumnPaddingLazy { ProjectEditPageStateContent(projectEditViewModel, mainViewModel) } }
    )

    DialogLoading(projectEditViewModel.loading)

    DialogMessage(
        visible =  dialogActive,
        dialog = dialogMessage
    )
}


@Composable
fun ProjectEditPageStateContent(
    projectEditViewModel: ProjectEditPageViewModel,
    mainViewModel: MainViewModel,
){
    val projectIdResponse by projectEditViewModel.projectIdResponse.collectAsState()

    StateHandler(
        viewModel = projectEditViewModel,
        listResponse = projectIdResponse,
        isSuccess = { ProjectEditPageContent(projectEditViewModel, mainViewModel) },
    )
}

@Composable
fun ProjectEditPageContent(
    projectEditViewModel: ProjectEditPageViewModel,
    mainViewModel: MainViewModel,
){
    val project by projectEditViewModel.projectId.collectAsState()
    val currentUser by mainViewModel.currentUser.collectAsState()

    ProjectForm(
        isEdit = true,
        currentProject = project,
        imageDataString = project?.image ?: "",
        onSubmit = { id, title, desc, tags, imageData, imageUri, context ->
            projectEditViewModel.editDialog {
                projectEditViewModel.handleEdit(id, title, desc, tags, imageData, currentUser, imageUri, context)
            }
        },
        onDelete = { id -> projectEditViewModel.deleteDialog(id) }
    )
}
