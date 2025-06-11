package org.d3if3121.tellink.ui.screen.content.component

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.navigation.MainNavGraph
import org.d3if3121.tellink.ui.component.BottomBar
import org.d3if3121.tellink.ui.component.MainScaffold
import org.d3if3121.tellink.ui.component.TopBar
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
){
    val currentUser by mainViewModel.currentUser.collectAsState()
    val lazyListState = rememberLazyListState()

    MainScaffold(
        topbar = { TopBar( lazyListState = lazyListState, helloActive = true, navController = navController, user = currentUser) },
        content = { MainNavGraph(lazyListState, currentUser) },
        bottombar = { BottomBar(navController = navController, home = true){} },
    )
}