package org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.ui.component.AddProjectImage
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.ColumnPadding
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.DisplayTag
import org.d3if3121.tellink.ui.component.DropdownTag
import org.d3if3121.tellink.ui.component.InputPutihNative
import org.d3if3121.tellink.ui.component.MainScaffold
import org.d3if3121.tellink.ui.component.RowEnd
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.SurfacePutih
import org.d3if3121.tellink.ui.component.TeksBoldMerah
import org.d3if3121.tellink.ui.component.TeksBoldTombol
import org.d3if3121.tellink.ui.component.TopBarContent
import org.d3if3121.tellink.ui.screen.content.component.StateHandlerAdd
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@Composable
fun ProjectAddPage(
    navController: NavController,
    mainViewModel: MainViewModel
){
    MainScaffold(
        content = {
            SurfacePutih {
                ProjectAddPageMainContent(navController, mainViewModel)
            }
        },
    )
}

@Composable
fun ProjectAddPageMainContent(
    navController: NavController,
    mainViewModel: MainViewModel
){

    val projectAddViewModel: ProjectAddPageViewModel = hiltViewModel()
    val projectAddResponse by projectAddViewModel.projectAddResponse.collectAsState()

    val dialogMessage by projectAddViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }

    LaunchedEffect(dialogMessage){
        dialogActive = dialogMessage.message.isNotEmpty()
    }

    StateHandlerAdd(
        viewModel = projectAddViewModel,
        response = projectAddResponse,
    )

    ColumnPadding(Modifier.fillMaxSize()){
        TopBarContent(
            text = "Add Post",
            icon = Icons.Filled.ArrowBackIosNew,
            navController = navController
        )

        Space(20)

        LazyColumn {
            item {
                ProjectAddPageContent(projectAddViewModel, mainViewModel)
            }
        }
    }

    DialogLoading(projectAddViewModel.loading)

    DialogMessage(
        visible =  dialogActive,
        textJudul =  dialogMessage.title,
        textDialog = dialogMessage.message,
        textTombol = "OK",
        onClick = {
            if(projectAddResponse is Success){ navController.popBackStack()
            } else { projectAddViewModel.dialogChange("", "") }
        }
    )
}

@Composable
fun ProjectAddPageContent(
    projectCrudViewModel: ProjectAddPageViewModel,
    mainViewModel: MainViewModel
){

    val currentUser by mainViewModel.currentUser.collectAsState()
    val context = LocalContext.current

    var judul by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var tag by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedTag by remember { mutableStateOf(listOf<String>()) }


    AddProjectImage(imageUri){ imageUri = it }

    Space(20)

    TeksBoldMerah("Title: ", Modifier.padding(bottom = 5.dp))

    InputPutihNative(
        input = judul,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { judul = it },
        keyboardType = KeyboardType.Text,
    )

    Space(20)

    TeksBoldMerah("Description: ", Modifier.padding(bottom = 5.dp))

    InputPutihNative(
        input = desc,
        expand = true,
        placeholder = stringResource(id = R.string.edit_title),
        onInputChange = { desc = it },
        keyboardType = KeyboardType.Text,
    )

    Space(20)

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
    )

    Space(20)

    RowEnd(Modifier.fillMaxWidth()){
        ButtonMerah(
            onClick = { projectCrudViewModel.handleAdd(judul, desc, selectedTag, currentUser, imageUri, context) },
            content = { TeksBoldTombol("Post") }
        )
    }
}

