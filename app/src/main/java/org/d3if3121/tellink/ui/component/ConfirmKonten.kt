package org.d3if3121.tellink.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.R
import org.d3if3121.tellink.core.printError
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.model.response.Response.Failure
import org.d3if3121.tellink.data.model.response.Response.Loading
import org.d3if3121.tellink.data.model.response.Response.Success
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import org.d3if3121.tellink.ui.viewmodel.ProjectListViewModel


@Composable
fun ConfirmKonten(
    navController: NavController,
    projectId: String,
    viewmodel: MahasiswaListViewModel = hiltViewModel(),
    projectviewmodel: ProjectListViewModel = hiltViewModel()
){
    var refreshKey by remember { mutableStateOf(0) }

    LaunchedEffect(refreshKey) {
        projectviewmodel.getProjectById(projectId)
    }
    Log.d("idproject", projectId)
    projectviewmodel.getProjectById(projectId)
    var project = projectviewmodel.project

    var judul by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var selectedTag by remember { mutableStateOf(listOf<String>()) }

    var secondmode by remember { mutableStateOf(false) }



    val context = LocalContext.current


    when(val updateProjectResponse = projectviewmodel.updateProjectResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Edit Success!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Project.route)
        }
        is Failure -> printError(updateProjectResponse.e)
        Response.Idle -> {}
    }

    when(val deleteProjectResponse = projectviewmodel.deleteProjectResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Delete Success!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Project.route)
        }
        is Failure -> printError(deleteProjectResponse.e)
        Response.Idle -> {}
    }

    when(val addAcceptResponse = projectviewmodel.addAcceptResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Accepted!", Toast.LENGTH_SHORT).show()
            projectviewmodel.resetAddAcceptResponse()
            refreshKey++
        }
        is Failure -> printError(addAcceptResponse.e)
        Response.Idle -> {}
    }

    when(val deleteAcceptResponse = projectviewmodel.deleteAcceptResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
            projectviewmodel.resetDeleteRequestResponse()
            refreshKey++
        }
        is Failure -> printError(deleteAcceptResponse.e)
        Response.Idle -> {}
    }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 17.dp, end = 17.dp, bottom = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        PilihanPutih(
            text1 = "Aktif",
            text2 = "Nonaktif",
            condition = secondmode,
            onclick1 = { secondmode = true },
            onclick2 = { secondmode = false }
        )

        LazyColumn{
            if (secondmode == false){
                items(
                    items = project.requests!!,
                    key = { it }
                ) { nim ->

                    val mahasiswa = viewmodel.mahasiswaMapProfile[nim] ?: Mahasiswa()

                    LaunchedEffect(nim) {
                        viewmodel.getMahasiswaByNimProfile(nim)
                    }

                    KartuProfilPutih(
                        fotoprofil = R.drawable.photo,
                        nama = mahasiswa.nama,
                        nim = mahasiswa.nim,
                        jurusan = mahasiswa.jurusan,

                        onclick = {
                            projectviewmodel.addAccept(projectId, nim)
                        }
                    )
                }
            } else {
                items(
                    items = project.accept!!,
                    key = { it }
                ) { nim ->

                    val mahasiswa = viewmodel.mahasiswaMapProfile[nim] ?: Mahasiswa()

                    LaunchedEffect(nim) {
                        viewmodel.getMahasiswaByNimProfile(nim)
                    }

                    KartuProfilPutih(
                        fotoprofil = R.drawable.photo,
                        nama = mahasiswa.nama,
                        nim = mahasiswa.nim,
                        jurusan = mahasiswa.jurusan,
                        accepted = true,

                        onclickkick = {
                            projectviewmodel.deleteAccept(projectId, nim)
                        }
                    )
                }
            }
        }
    }
}
