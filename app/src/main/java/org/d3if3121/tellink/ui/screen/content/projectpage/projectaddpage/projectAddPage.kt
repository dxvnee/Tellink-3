package org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage

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
import org.d3if3121.tellink.ui.screen.content.component.StateHandlerPost
import org.d3if3121.tellink.ui.screen.content.projectpage.component.ProjectForm
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@Composable
fun ProjectAddPage(
    navController: NavController,
    mainViewModel: MainViewModel
){
    val projectAddViewModel: ProjectAddPageViewModel = hiltViewModel()
    val projectAddResponse by projectAddViewModel.projectAddResponse.collectAsState()

    val dialogMessage by projectAddViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }


    LaunchedEffect(dialogMessage){ dialogActive = dialogMessage.message.isNotEmpty() }

    StateHandlerPost(
        viewModel = projectAddViewModel,
        response = projectAddResponse,
        navController = navController,
        dialogInterface = DialogStateInterface.ADD
    )

    ScaffoldSurfacePutih(
        topbar = {
            TopBarContent(
                text = "Add Post",
                icon = Icons.Filled.ArrowBackIosNew,
                navController = navController
            )
        },
        content = { ColumnPaddingLazy { ProjectAddPageContent(projectAddViewModel, mainViewModel) } }
    )

    DialogLoading(projectAddViewModel.loading)

    DialogMessage(
        visible =  dialogActive,
        dialog = dialogMessage
    )
}


@Composable
fun ProjectAddPageContent(
    projectCrudViewModel: ProjectAddPageViewModel,
    mainViewModel: MainViewModel
){
    val currentUser by mainViewModel.currentUser.collectAsState()

    ProjectForm(
        isEdit = false,
        onSubmit = { id, title, desc, tags, imageData, imageUri, context ->
            projectCrudViewModel.handleAdd(title, desc, tags, currentUser, imageUri, context)
        }
    )
}

