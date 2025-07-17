package org.d3if3121.tellink.ui.screen.content.projectpage.projecteditpage

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.dialog.DialogInterface
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.ui.component.AddProjectImage
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.ColumnPaddingLazy
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.DisplayTag
import org.d3if3121.tellink.ui.component.DropdownTag
import org.d3if3121.tellink.ui.component.InputPutihNative
import org.d3if3121.tellink.ui.component.RowEnd
import org.d3if3121.tellink.ui.component.ScaffoldSurfacePutih
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.SpaceWidth
import org.d3if3121.tellink.ui.component.TeksBoldMerah
import org.d3if3121.tellink.ui.component.TeksBoldTombol
import org.d3if3121.tellink.ui.component.TopBarContent
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.component.StateHandlerPost
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

    StateHandlerPost(viewModel = projectEditViewModel, response = projectEditResponse, DialogInterface.Edit)
    StateHandlerPost(viewModel = projectEditViewModel, response = projectDeleteResponse, DialogInterface.Delete)

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
        textJudul =  dialogMessage.title,
        textDialog = dialogMessage.message,
        textTombol = "OK",
        onClick = {
            when {
                projectEditResponse is Success || projectDeleteResponse is Success -> { navController.popBackStack() }
                projectEditResponse is Failure || projectDeleteResponse is Failure -> { projectEditViewModel.dialogChange("", "") }
            }
        }
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
    val context = LocalContext.current

    val project by projectEditViewModel.projectId.collectAsState()
    val currentUser by mainViewModel.currentUser.collectAsState()
    var imageData by remember { mutableStateOf("") }

    var id by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var tag by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedTag by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(key1 = project, key2 = imageData) {
        id = project?.id ?: ""
        judul = project?.title ?: ""
        desc = project?.desc ?: ""
        imageData = project?.image ?: ""
        selectedTag = project?.tag ?: listOf()
        Log.d("keren", project?.tag.toString())
    }

    AddProjectImage(
        imageUri = imageUri,
        gambarString = imageData
    ){ imageUri = it };  Space(20)

    TeksBoldMerah("Title: ", Modifier.padding(bottom = 5.dp))

    InputPutihNative(
        input = judul,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { judul = it },
        keyboardType = KeyboardType.Text,
    ); Space(20)

    TeksBoldMerah("Description: ", Modifier.padding(bottom = 5.dp))

    InputPutihNative(
        input = desc,
        expand = true,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { desc = it },
        keyboardType = KeyboardType.Text,
    ); Space(20)

    TeksBoldMerah("Tags: ", Modifier)

    if (selectedTag.isNotEmpty()){ Space(5) }

    DisplayTag(
        selectedTag = selectedTag,
        modifier = Modifier.padding(bottom = 10.dp),
        onTagRemove = { tag -> selectedTag = selectedTag - tag }
    )

    DropdownTag(
        selectedTag = tag,
        onTagSelected = { tag ->
            if (selectedTag.size < 3){ selectedTag = selectedTag + tag }
        }
    ); Space(20)

    RowEnd(Modifier.fillMaxWidth()){
        ButtonMerah(
            onClick = { projectEditViewModel.deleteProject(id) },
            content = { TeksBoldTombol("Delete") }
        ); SpaceWidth(10)

        ButtonMerah(
            onClick = { projectEditViewModel.handleEdit(id, judul, desc, selectedTag, imageData, currentUser, imageUri, context) },
            content = { TeksBoldTombol("Edit") }
        )
    }
}

