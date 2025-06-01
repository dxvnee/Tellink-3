package org.d3if3121.tellink.ui.screen.homepage

import android.annotation.SuppressLint
import android.content.Context
import org.d3if3121.tellink.R
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if3121.tellink.ui.theme.Warna
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.d3if3121.tellink.components.LoadingIndicator
import org.d3if3121.tellink.core.printError
import org.d3if3121.tellink.data.model.Mahasiswa
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.Response
import org.d3if3121.tellink.data.model.Response.Success
import org.d3if3121.tellink.data.model.Response.Loading
import org.d3if3121.tellink.data.model.Response.Failure
import org.d3if3121.tellink.ui.component.BottomBar
import org.d3if3121.tellink.ui.component.InputPutihSearch
import org.d3if3121.tellink.ui.component.KartuKonten
import org.d3if3121.tellink.ui.component.TeksJudulMerah
import org.d3if3121.tellink.ui.component.TopBar
import org.d3if3121.tellink.ui.component.cekScroll
import org.d3if3121.tellink.ui.screen.projectpage.formatRelativeTime
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import org.d3if3121.tellink.ui.viewmodel.MainViewModel
import org.d3if3121.tellink.ui.viewmodel.ProjectListViewModel



val TOP_BAR_HEIGHT = 70.dp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: MahasiswaListViewModel = hiltViewModel(),
    viewModel2: MainViewModel = hiltViewModel(),
    projectviewmodel: ProjectListViewModel = hiltViewModel()
) {

    val currentUser by viewModel2.currentUser.collectAsState()
    MainScaffold(
        content = { paddingValues, lazyListState, user ->

            Log.d("enakkkkk", currentUser.nim)
//            MainContentHome(
//                navController = navController,
//                lazyListState = lazyListState,
//                paddingValues = paddingValues,
//                viewmodel = viewModel,
//                projectviewmodel = projectviewmodel,
//                viewModel = TODO(),
//                user = user
//            )
        },
        viewModel = viewModel,
        navController = navController,
    )
}

@Composable
fun MainScaffold(
    content: @Composable (PaddingValues, LazyListState, Mahasiswa) -> Unit,
    viewModel: MahasiswaListViewModel = hiltViewModel(),
    navController: NavHostController
){
    val lazyListState = rememberLazyListState()
    var user = viewModel.user

    Scaffold(
        topBar = {
            TopBar(
                lazyListState = lazyListState,
                helloActive = true,
                navController = navController,
                viewmodel = viewModel
            )
        },
        content = { paddingValues ->
            content(paddingValues, lazyListState, user)
        },
        bottomBar = {
            BottomBar(navController = navController, home = true){}
        },
        contentColor = Warna.PutihNormal
    )
}

//
//@Composable
//fun MainContentHome(
//    navController: NavHostController,
//    lazyListState: LazyListState,
//    paddingValues: PaddingValues,
//    viewmodel: MahasiswaListViewModel,
//    projectviewmodel: ProjectListViewModel,
//    viewModel: MahasiswaListViewModel = hiltViewModel(),
//    user: Mahasiswa
//) {
//
//    projectviewmodel.getProjectListUser(user.nim)
//    when (val projectListUserResponse = projectviewmodel.projectListUserResponse) {
//        is Loading -> LoadingIndicator()
//        is Success -> projectListUserResponse.data.let { projectList ->
//
//            Log.d("HASILNYA", projectList.toString())
//            Column(modifier = Modifier.background(color = Warna.PutihNormal)) {
//
//            }
//
//        }
//
//        is Failure -> printError(projectListUserResponse.e)
//        Response.Idle -> {}
//    }
//
//    var context = LocalContext.current
//    val padding by animateDpAsState(targetValue = if (cekScroll(lazyListState)) 0.dp else TOP_BAR_HEIGHT, animationSpec = tween(durationMillis = 500))
//    var search by remember { mutableStateOf("") }
//
//    HomePageResponse(projectviewmodel, context)
//
//    LazyColumn(
//        modifier = Modifier.padding(top = padding, start = 17.dp, end = 17.dp).fillMaxWidth().fillMaxHeight().background(color = Warna.PutihNormal),
//        state = lazyListState
//    ){
//        item {
//            TeksJudulMerah(text = "Welcome to Tellink", Modifier.padding(top = 20.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start,
//                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 17.dp)
//            ){
//                InputPutihSearch(
//                    input = search,
//                    placeholder = stringResource(id = R.string.search),
//                    onInputChange = { search = it },
//                    keyboardType = KeyboardType.Number,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//
//        if (projectList.isEmpty()) {
//            item {
//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier.fillMaxSize().height(550.dp)
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "You're up to date!",
//                            color = Warna.MerahNormal,
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight.Normal,
//                        )
//                    }
//
//                }
//            }
//        } else {
//            items(
//                items = projectList,
//            ){ project ->
//                val mahasiswa = viewmodel.mahasiswaMap[project.nim] ?: Mahasiswa()
//                var requestornot by remember { mutableStateOf(false) }
//
//                LaunchedEffect(project.id, user.nim) {
//                    requestornot = viewmodel.checkRequestProject(project.id!!, user.nim).await()
//                }
//                LaunchedEffect(project.nim) {
//                    viewmodel.getMahasiswaByNim(project.nim)
//                    viewmodel.addViewedProject(project.id!!)
//                }
//
//                KartuKonten(
//                    fotoprofil = R.drawable.photo,
//                    nama = mahasiswa.nama,
//                    jurusan = mahasiswa.jurusan,
//                    hari = formatRelativeTime(project.date!!),
//                    judul = project.title,
//                    gambar = project.image ?: "",
//                    konten = project.desc,
//                    request = requestornot,
//                    requests = project.requests!!,
//                    tag = project.tag,
//                    onrequestchangefalse = {
//                        requestornot = false
//                    },
//                    onrequestchangetrue = {
//                        requestornot = true
//                    },
//                    onclick = {
//                        projectviewmodel.addRequest(project.id!!, user.nim)
//                    },
//                    onclickcancel = {
//                        projectviewmodel.deleteRequest(project.id!!, user.nim)
//                    }
//                )
//                Spacer(modifier = Modifier.height(20.dp))
//            }
//        }
//    }
//
//
//
//}

@Composable
fun HomePageResponse(projectviewmodel: ProjectListViewModel, context: Context){
    when(val addRequestResponse = projectviewmodel.addRequestResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Request Success!", Toast.LENGTH_SHORT).show()
            projectviewmodel.resetAddRequestResponse()
        }
        is Failure -> printError(addRequestResponse.e)
        Response.Idle -> {}
    }
    when(val deleteRequestResponse = projectviewmodel.deleteRequestResponse){
        is Loading -> {

        }
        is Success -> {
            Toast.makeText(context, "Request Cancelled.", Toast.LENGTH_SHORT).show()
            projectviewmodel.resetDeleteRequestResponse()
        }
        is Failure -> printError(deleteRequestResponse.e)
        Response.Idle -> {}
    }
}


