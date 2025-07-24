@file:JvmName("MainNavGraphKt")

package org.d3if3121.tellink.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.animation.animationFadeScaleIn
import org.d3if3121.tellink.ui.animation.animationFadeScaleOut
import org.d3if3121.tellink.ui.component.topbar.TopbarType
import org.d3if3121.tellink.ui.screen.content.homepage.HomePage
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.d3if3121.tellink.ui.screen.content.projectpage.ProjectPage
import org.d3if3121.tellink.ui.screen.content.projectpage.ProjectPageViewModel
import org.d3if3121.tellink.ui.viewmodel.MainViewModel

@Composable
fun MainNavGraph(
    currentUser: Mahasiswa,
    navControllerGlobal: NavHostController,

    lazyListState: LazyListState,
    lazyListState2: LazyListState,

    homeViewModel: HomeViewModel = hiltViewModel(),
    projectViewModel: ProjectPageViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),

    onTopbartypeChange: (TopbarType) -> Unit,
    navControllerContent: (NavHostController) -> Unit
){
    val navController = rememberNavController()

    navControllerContent(navController)

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { animationFadeScaleIn() },
        exitTransition = { animationFadeScaleOut() },
    ){
        composable(route = Screen.Home.route){
            HomePage(lazyListState = lazyListState, navControllerGlobal = navControllerGlobal, homeViewModel = homeViewModel, mainViewModel = mainViewModel)
            onTopbartypeChange(TopbarType.HOME)
        }

        composable(route = Screen.Project.route){
            ProjectPage(
                navControllerGlobal = navControllerGlobal,
                lazyListState = lazyListState2,
                currentUser = currentUser,
                projectViewModel = projectViewModel,
                mainViewModel = mainViewModel,
            )
            onTopbartypeChange(TopbarType.PROJECT)
        }
    }
}