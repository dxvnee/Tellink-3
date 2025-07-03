package org.d3if3121.tellink.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.animation.animationFadeScaleIn
import org.d3if3121.tellink.ui.animation.animationFadeScaleOut
import org.d3if3121.tellink.ui.screen.auth.login.LoginPage
import org.d3if3121.tellink.ui.screen.auth.register.RegisterPage
import org.d3if3121.tellink.ui.screen.content.MainPage
import org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage.ProjectAddPage
import org.d3if3121.tellink.ui.viewmodel.MainViewModel


@Composable
fun GlobalNavGraph(){
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        enterTransition = { animationFadeScaleIn() },
        exitTransition = { animationFadeScaleOut() },
    ) {
        composable(route = Screen.Login.route){
            LoginPage(navController, mainViewModel)
        }

        composable(route = Screen.Register.route){
            RegisterPage(navController)
        }
        composable(route = Screen.Main.route){
            MainPage(navController, mainViewModel)
        }
        composable(route = Screen.ProjectAdd.route){
            ProjectAddPage(navController, mainViewModel)
        }

    }
}