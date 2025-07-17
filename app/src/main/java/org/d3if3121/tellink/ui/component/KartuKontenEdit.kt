package org.d3if3121.tellink.ui.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import org.d3if3121.tellink.ui.viewmodel.ProjectListViewModel


@Composable
fun KartuKontenEdit(
    navController: NavController,
    projectId: String,
    viewmodel: MahasiswaListViewModel = hiltViewModel(),
    projectviewmodel: ProjectListViewModel = hiltViewModel()
){
//    Log.d("idproject", projectId)
//    projectviewmodel.getProjectById(projectId)
//    var project = projectviewmodel.project
//
//    var judul by remember { mutableStateOf("") }
//    var desc by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf("") }
//    var selectedTag by remember { mutableStateOf(listOf<String>()) }
//
//    val context = LocalContext.current
//
//    when(val updateProjectResponse = projectviewmodel.updateProjectResponse){
//        is Loading -> {
//
//        }
//        is Success -> {
//            Toast.makeText(context, "Edit Success!", Toast.LENGTH_SHORT).show()
//            navController.navigate(Screen.Project.route)
//        }
//        is Failure -> printError(updateProjectResponse.e)
//        Response.Idle -> {}
//    }
//
//    when(val deleteProjectResponse = projectviewmodel.deleteProjectResponse){
//        is Loading -> {
//
//        }
//        is Success -> {
//            Toast.makeText(context, "Delete Success!", Toast.LENGTH_SHORT).show()
//            navController.navigate(Screen.Project.route)
//        }
//        is Failure -> printError(deleteProjectResponse.e)
//        Response.Idle -> {}
//    }


}
