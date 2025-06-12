@file:JvmName("MainNavGraphKt")

package org.d3if3121.tellink.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import org.d3if3121.tellink.ui.screen.content.ConfirmPage
import org.d3if3121.tellink.ui.screen.content.EditPage
import org.d3if3121.tellink.ui.screen.content.ProfilePage
import org.d3if3121.tellink.ui.screen.content.homepage.HomePage
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.d3if3121.tellink.ui.screen.content.projectpage.ProjectPage
import org.d3if3121.tellink.ui.viewmodel.MahasiswaListViewModel
import org.d3if3121.tellink.ui.viewmodel.ProjectListViewModel

@Composable
fun MainNavGraph(
    lazyListState: LazyListState,
    currentUser: Mahasiswa,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onTopbartypeChange: (TopbarType) -> Unit,
){
    val navController = rememberNavController()

    val mahasiswaListViewModel: MahasiswaListViewModel = hiltViewModel()
    val projectListViewModel: ProjectListViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { animationFadeScaleIn() },
        exitTransition = { animationFadeScaleOut() },
    ){
        composable(route = Screen.Home.route){
            HomePage(lazyListState = lazyListState, homeViewModel)
            onTopbartypeChange(TopbarType.HOME)
        }

        composable(route = Screen.Project.route){
            ProjectPage(navController, mahasiswaListViewModel, projectListViewModel)
            onTopbartypeChange(TopbarType.PROJECT)
        }

        composable(route = Screen.Profile.route){
            ProfilePage(navController, mahasiswaListViewModel)
            onTopbartypeChange(TopbarType.PROFILE)
        }

        composable(route ="${Screen.EditProject.route}/{projectId}",
            arguments = listOf(navArgument("projectId"){
                type = NavType.StringType
                nullable = false
            })
        ){ backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            EditPage(navController = navController, projectId = projectId)
        }

        composable(route ="${Screen.ConfirmPage.route}/{projectId}",
            arguments = listOf(navArgument("projectId"){
                type = NavType.StringType
                nullable = false
            })
        ){ backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            ConfirmPage(navController = navController, projectId = projectId)
        }
    }
}