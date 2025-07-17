//package org.d3if3121.tellink.ui.screen.content.projectpage.requestpage
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.navigation.NavController
//import org.d3if3121.tellink.ui.component.DialogLoading
//import org.d3if3121.tellink.ui.component.DialogMessage
//import org.d3if3121.tellink.ui.component.ScaffoldSurfacePutih
//import org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage.ProjectAddPageContent
//import org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage.ScaffoldContent
//
//@Composable
//fun RequestPage(navController: NavController){
//    ScaffoldSurfacePutih { RequestPageMainContent(navController) }
//}
//
//@Composable
//fun RequestPageMainContent(navController: NavController){
//
//    val dialogMessage by projectAddViewModel.dialogMessage.collectAsState()
//    val dialogActive by remember { mutableStateOf(false) }
//
//    LaunchedEffect(){  }
//
//    DialogMessage(
//        visible = dialogActive,
//        textJudul = dialogMessage,
//        textDialog = dialogMessage,
//        textTombol = "OK"
//    ){ dialogMessage = false }
//
//    ScaffoldContent(navController) {
//        LazyColumn { item { ProjectAddPageContent(projectAddViewModel, mainViewModel) } }
//    }
//
//    DialogMessage(
//        visible = dialogActive,
//        textJudul = dialogMessage,
//        textDialog = dialogMessage,
//        textTombol = "OK"
//    ){ dialogMessage = false }
//
//
//    DialogLoading(projectAddViewModel.loading)
//
//}