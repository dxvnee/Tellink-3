package org.d3if3121.tellink.ui.screen.content

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.navigation.MainNavGraph
import org.d3if3121.tellink.ui.component.BottomBar
import org.d3if3121.tellink.ui.component.BoxWarna
import org.d3if3121.tellink.ui.component.DialogGambar
import org.d3if3121.tellink.ui.component.MainScaffold
import org.d3if3121.tellink.ui.component.topbar.TopBar
import org.d3if3121.tellink.ui.component.topbar.TopbarType
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
){
    val currentUser by mainViewModel.currentUser.collectAsState()
    var currentTopbarType by remember { mutableStateOf(TopbarType.HOME) }
    val lazyListState = rememberLazyListState()
    val lazyListStateProject = rememberLazyListState()
    val homeViewModel: HomeViewModel = hiltViewModel()

    var navControllerContent = remember { mutableStateOf<NavHostController?>(null) }

    DialogGambar(homeViewModel)

    MainScaffold(
        topbar = {
            TopBar(
                lazyListState = lazyListState,
                lazyListStateProject = lazyListStateProject,
                topbarType = currentTopbarType,
                navController = navController,
                mainViewModel = mainViewModel
            )
        },
        content = {
            BoxWarna(Warna.PutihGelap){
                MainNavGraph(
                    currentUser = currentUser,
                    navControllerGlobal = navController,

                    lazyListState = lazyListState,
                    lazyListState2 = lazyListStateProject,

                    homeViewModel = homeViewModel,
                    mainViewModel = mainViewModel,

                    onTopbartypeChange = { currentTopbarType = it },
                    navControllerContent = { navControllerContent.value = it }
                )
            }
        },
        bottombar = {
            BottomBar(
                navController = navControllerContent.value,
                home = true
            )
        }
    )
}