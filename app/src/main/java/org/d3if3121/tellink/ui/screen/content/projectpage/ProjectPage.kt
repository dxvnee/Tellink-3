package org.d3if3121.tellink.ui.screen.content.projectpage

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import org.d3if3121.tellink.ui.viewmodel.ProjectListViewModel
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProjectPage(
    navController: NavHostController,
    viewmodel: MahasiswaListViewModel = hiltViewModel(),
    projectviewmodel: ProjectListViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()

//    Scaffold(
//        topBar = { TopBar(lazyListState = lazyListState, helloActive = false, TOP_BAR_ZERO = 70 },
//        content = { paddingValues ->
//            MainContentProject(
//                lazyListState = lazyListState,
//                paddingValues = paddingValues,
//                viewmodel = viewmodel,
//                projectviewmodel = projectviewmodel,
//                navController = navController
//            )
//        },
//        bottomBar = { BottomBar(navController = navController) }
//    )
}


//
//
//@Composable
//fun MainContentProject(
//    lazyListState: LazyListState,
//    paddingValues: PaddingValues,
//    viewmodel: MahasiswaListViewModel = hiltViewModel(),
//    projectviewmodel: ProjectListViewModel = hiltViewModel(),
//    navController: NavHostController
//) {
//    val user = viewmodel.user
//    val padding by animateDpAsState(targetValue = if (cekScroll(lazyListState)) 0.dp else TOP_BAR_HEIGHT, animationSpec = tween(durationMillis = 500))
//    var showDialog by remember { mutableStateOf(false) }
//    var refreshData by remember { mutableStateOf(false) }
//    var secondmode by remember { mutableStateOf(false) }
//
//    LaunchedEffect(refreshData) {
//        projectviewmodel.getProjectListByNim(user.nim)
//        projectviewmodel.getProjectList()
//    }
//
////    DialogLoading(projectviewmodel)
//
//    TambahProjectDialog(
//        showDialog, viewmodel, projectviewmodel,
//        onRefreshTrue = { refreshData = true },
//        onDialogFalse = { showDialog = false }
//    )
//
//    LazyColumn(
//        modifier = Modifier.padding(top = padding, start = 17.dp, end = 17.dp).fillMaxWidth().fillMaxHeight(),
//        state = lazyListState
//    ) {
//        item {
//            TopSectionProject(
//                secondmode = secondmode,
//                onShowDialogChange = { showDialog = true },
//                onSecondmodeChange = { showDialog  = it },
//            )
//        }
//
//        when (secondmode) {
//            true -> {
//                items(items = user.accept!!){ projectId ->
//                    RequestProjectSection(viewmodel, projectviewmodel, projectId, user)
//                }
//                items(items = user.requests!!) { projectId ->
//                    ProjectSection(viewmodel, projectviewmodel, projectId, user)
//                }
//            }
//
//            false -> {
//                when (val projectListByNimResponse = projectviewmodel.projectListByNimResponse) {
//                    is Loading -> { projectviewmodel.changeLoading(true) }
//                    is Failure -> printError(projectListByNimResponse.e)
//                    is Success -> projectListByNimResponse.data.let { projectList ->
//                        if (projectList!!.isEmpty()) {
//                            item {
//                                ProjectKosong()
//                            }
//                        } else {
//                            items(items = projectList) { project ->
//                                MyProjectSection(viewmodel, navController, project)
//                            }
//                        }
//                    }
//                    Response.Idle -> {}
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MyProjectSection(
//    viewmodel: MahasiswaListViewModel,
//    navController: NavHostController,
//    project: Project
//){
//    val mahasiswa = viewmodel.mahasiswaMap[project.nim] ?: Mahasiswa()
//
//    LaunchedEffect(project.nim) {
//        viewmodel.getMahasiswaByNim(project.nim)
//    }
//
//    KartuKonten(
//        fotoprofil = R.drawable.photo,
//        nama = mahasiswa.nama,
//        jurusan = mahasiswa.jurusan,
//        hari = formatRelativeTime(project.date!!),
//        type = "project",
//        tag = project.tag,
//        judul = project.title,
//        gambar = project.image ?: "",
//        konten = project.desc,
//        onclick = { navController.navigate("${Screen.EditProject.route}/${project.id}") },
//        onclicktext = { navController.navigate("${Screen.ConfirmPage.route}/${project.id}") }
//    )
//
//    Spacer(modifier = Modifier.height(20.dp))
//}
//
//@Composable
//fun ProjectSection(
//    viewmodel: MahasiswaListViewModel,
//    projectviewmodel: ProjectListViewModel,
//    projectId: String,
//    user: Mahasiswa
//
//){
//    projectviewmodel.getProjectById(projectId)
//
//    val project = projectviewmodel.projectMap[projectId] ?: Project()
//    val mahasiswa = viewmodel.mahasiswaMap[project.nim] ?: Mahasiswa()
//    var requestornot by remember { mutableStateOf(false) }
//
//    LaunchedEffect(projectId, project.nim) {
//        viewmodel.getMahasiswaByNim(project.nim)
//        projectviewmodel.getProjectById(projectId)
//        requestornot = viewmodel.checkRequestProject(project.id!!, user.nim).await()
//    }
//
//    KartuKonten(
//        fotoprofil = R.drawable.photo,
//        nama = mahasiswa.nama,
//        jurusan = mahasiswa.jurusan,
//        hari = formatRelativeTime(project.date!!),
//        judul = project.title,
//        gambar = project.image ?: "",
//        konten = project.desc,
//        request = requestornot,
//        requests = project.requests!!,
//        tag = project.tag,
//        onrequestchangefalse = { requestornot = false },
//        onrequestchangetrue = { requestornot = true },
//        onclick = { projectviewmodel.addRequest(project.id!!, user.nim) },
//        onclickcancel = { projectviewmodel.deleteRequest(project.id!!, user.nim) }
//    )
//
//    Spacer(modifier = Modifier.height(20.dp))
//}
//@Composable
//fun RequestProjectSection(
//    viewmodel: MahasiswaListViewModel,
//    projectviewmodel: ProjectListViewModel,
//    projectId: String,
//    user: Mahasiswa
//){
//    projectviewmodel.getProjectById(projectId)
//
//    var project by remember { mutableStateOf(Project()) }
//    var mahasiswa by remember { mutableStateOf(Mahasiswa()) }
//    var requestornot by remember { mutableStateOf(false) }
//
//    LaunchedEffect(projectId) {
//        project = projectviewmodel.getProjectByIdSuspend(projectId)
//        mahasiswa = viewmodel.getMahasiswaByNimSuspend(project.nim)
//        requestornot = viewmodel.checkRequestProject(project.id!!, user.nim).await()
//    }
//
//    KartuKonten(
//        fotoprofil = R.drawable.photo,
//        nama = mahasiswa.nama,
//        jurusan = mahasiswa.jurusan,
//        hari = formatRelativeTime(project.date!!),
//        type = "accept",
//        judul = project.title,
//        gambar = project.image ?: "",
//        konten = project.desc,
//        requests = project.requests!!,
//        tag = project.tag,
//
//        onclick = {},
//    )
//
//    Spacer(modifier = Modifier.height(20.dp))
//}
//
//
//
//
//@Composable
//fun ProjectKosong() {
//    Column (
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize().height(550.dp)
//    ){
//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ){
//            Text(
//                text = "Project doesn't exist!",
//                color = Warna.MerahNormal,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//            )
//        }
//
//    }
//}
//
//@Composable
//fun     TopSectionProject(
//    secondmode: Boolean,
//    onShowDialogChange: () -> Unit,
//    onSecondmodeChange: (Boolean) -> Unit
//){
//
//    var search by remember { mutableStateOf("") }
//
//
//    TeksJudulMerah("My Project", Modifier.padding(top = 20.dp, bottom = 10.dp))
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start,
//        modifier = Modifier.fillMaxWidth().padding(bottom = 17.dp)
//    ) {
//        InputPutihSearch(
//            input = search,
//            placeholder = stringResource(id = R.string.search),
//            onInputChange = { search = it },
//            keyboardType = KeyboardType.Number,
//            modifier = Modifier.width(297.dp)
//        )
//
//        TombolTambah{ onShowDialogChange() }
//    }
//
//    PilihanPutih(
//        text1 = "My Project",
//        text2 = "Requested",
//        condition = secondmode,
//        color1 = if (secondmode) { CustomButtonColors.MerahPutih } else { CustomButtonColors.PutihMerah },
//        color2 = if (secondmode) { CustomButtonColors.PutihMerah } else { CustomButtonColors.MerahPutih },
//        onclick1 = { onSecondmodeChange(false) },
//        onclick2 = { onSecondmodeChange(true) }
//    )
//}

fun formatRelativeTime(dateTimeString: String): String {
    if (dateTimeString == "") {
        return "unknown time"
    } else {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val parsedDateTime = LocalDateTime.parse(dateTimeString, formatter)

        val now = LocalDateTime.now(ZoneId.systemDefault())
        val duration = Duration.between(parsedDateTime, now)

        return when {
            duration.toMinutes() < 1 -> "just now"
            duration.toHours() < 1 -> "${duration.toMinutes()} minutes ago"
            duration.toDays() < 1 -> "${duration.toHours()} hours ago"
            duration.toDays() < 7 -> "${duration.toDays()} days ago"
            duration.toDays() < 30 -> "${duration.toDays() / 7} weeks ago"
            duration.toDays() < 365 -> "${duration.toDays() / 30} months ago"
            else -> "${duration.toDays() / 365} years ago"
        }
    }


}




