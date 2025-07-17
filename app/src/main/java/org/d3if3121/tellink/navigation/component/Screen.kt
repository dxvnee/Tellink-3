package org.d3if3121.tellink.navigation.component

sealed class Screen(val route: String) {
    data object Login: Screen("LoginPage")
    data object Register: Screen("RegisterPage")
    data object Main: Screen("MainPage")

    data object Home: Screen("HomePage")

    data object Project: Screen("ProjectPage")
    data object ProjectAdd: Screen("ProjectAddPage")
    data object ProjectEdit: Screen("ProjectEditPage")

    data object Profile: Screen("ProfilePage")
    data object EditProject: Screen("EditProjectPage")
    data object ConfirmPage: Screen("ConfirmPage")
}
