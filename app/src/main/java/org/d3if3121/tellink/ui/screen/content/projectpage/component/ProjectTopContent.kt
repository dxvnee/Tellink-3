package org.d3if3121.tellink.ui.screen.content.projectpage.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3121.tellink.ui.component.ColumnPaddingKiriKanan
import org.d3if3121.tellink.ui.component.PilihanPutih
import org.d3if3121.tellink.ui.component.SearchBarWithButton
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.TeksJudulHitam
import org.d3if3121.tellink.ui.screen.content.projectpage.ProjectPageViewModel


@Composable
fun ProjectTopContent(
    projectPageViewModel: ProjectPageViewModel
){
    val secondPage by projectPageViewModel.secondPage.collectAsState()

    ColumnPaddingKiriKanan {
        TeksJudulHitam("My Project", Modifier.padding(top = 20.dp, bottom = 10.dp))

        PilihanPutih(
            condition = secondPage,
            text1 = "My Project",
            text2 = "Requested",
            onclick1 = { projectPageViewModel.secondPageChange( false ) },
            onclick2 = { projectPageViewModel.secondPageChange(true ) }
        )
        Space(10)

    }
}

@Composable
fun ProjectTopContentSecondary(
    search: String,
    onSearchChange: (String) -> Unit,
    navControllerGlobal: NavHostController,
){
    ColumnPaddingKiriKanan {
        SearchBarWithButton(
            search = search,
            onSearchChange = { onSearchChange(it) },
            navControllerGlobal = navControllerGlobal,
        )
    }
}
